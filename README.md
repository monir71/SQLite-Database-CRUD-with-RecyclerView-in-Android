Helper Class:

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TBL_NAME = "students";
    private static final String STUDENTS_COL_STUDENT_ID = "student_id";
    private static final String STUDENTS_COL_ROLL_NUMBER = "roll_number";
    private static final String STUDENTS_COL_REGISTRATION_NUMBER = "registration_number";
    private static final String STUDENTS_COL_NAME = "name";
    private static final String STUDENTS_COL_DOB = "dob";
    private static final String STUDENTS_COL_ENROLLMENT_CLASS = "enrollment_class";
    private static final String STUDENTS_COL_STUDY_GROUP = "study_group";
    private static final String STUDENTS_COL_FATHER_NAME = "father_name";
    private static final String STUDENTS_COL_MOTHER_NAME = "mother_name";
    private static final String STUDENTS_COL_SEX = "sex";
    private static final String STUDENTS_COL_FULL_ADDRESS = "full_address";
    private static final String STUDENTS_COL_DISTRICT = "district";
    private static final String STUDENTS_COL_COUNTRY = "country";

    //student_id, roll_number, registration_number, name, dob, enrollment_class, study_group, father_name, mother_name, sex, full_address, district, country

    public MySQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TBL_NAME + " ( " +
                STUDENTS_COL_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENTS_COL_ROLL_NUMBER + " INTEGER NOT NULL, " +
                STUDENTS_COL_REGISTRATION_NUMBER + " INTEGER NOT NULL, " +
                STUDENTS_COL_NAME + " TEXT NOT NULL, " +
                STUDENTS_COL_DOB + " TEXT NOT NULL, " +
                STUDENTS_COL_ENROLLMENT_CLASS + " TEXT NOT NULL, " +
                STUDENTS_COL_STUDY_GROUP + " TEXT NOT NULL, " +
                STUDENTS_COL_FATHER_NAME + " TEXT NOT NULL, " +
                STUDENTS_COL_MOTHER_NAME + " TEXT NOT NULL, " +
                STUDENTS_COL_SEX + " TEXT NOT NULL, " +
                STUDENTS_COL_FULL_ADDRESS + " TEXT NOT NULL, " +
                STUDENTS_COL_DISTRICT + " TEXT NOT NULL, " +
                STUDENTS_COL_COUNTRY + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(int roll_number, int registration_number, String name, String dob, String enrollment_class,
                           String study_group, String father_name, String mother_name, String sex, String full_address, String district, String country)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENTS_COL_ROLL_NUMBER, roll_number);
        values.put(STUDENTS_COL_REGISTRATION_NUMBER, registration_number);
        values.put(STUDENTS_COL_NAME, name);
        values.put(STUDENTS_COL_DOB, dob);
        values.put(STUDENTS_COL_ENROLLMENT_CLASS, enrollment_class);
        values.put(STUDENTS_COL_STUDY_GROUP, study_group);
        values.put(STUDENTS_COL_FATHER_NAME, father_name);
        values.put(STUDENTS_COL_MOTHER_NAME, mother_name);
        values.put(STUDENTS_COL_SEX, sex);
        values.put(STUDENTS_COL_FULL_ADDRESS, full_address);
        values.put(STUDENTS_COL_DISTRICT, district);
        values.put(STUDENTS_COL_COUNTRY, country);

        db.insert(TBL_NAME, null, values);
    }

    public void updateData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENTS_COL_STUDY_GROUP, "Software Engineering");
        db.update(TBL_NAME, values, STUDENTS_COL_STUDENT_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<MyModel> readData()
    {
        ArrayList<MyModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, null);
        while(cursor.moveToNext())
        {
            MyModel model = new MyModel();
            model.student_id = cursor.getInt(0);
            model.roll_number = cursor.getInt(1);
            model.registration_number = cursor.getInt(2);
            model.name = cursor.getString(3);
            model.dob = cursor.getString(4);
            model.enrollment_class = cursor.getString(5);
            model.study_group = cursor.getString(6);
            model.father_name = cursor.getString(7);
            model.mother_name = cursor.getString(8);
            model.sex = cursor.getString(9);
            model.full_address = cursor.getString(10);
            model.district = cursor.getString(11);
            model.country = cursor.getString(12);
            data.add(model);
        }
        return data;
    }

    public void deleteData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBL_NAME, STUDENTS_COL_STUDENT_ID + " = ?", new String[]{String.valueOf(id)});
    }
}


Model Class:

public class MyModel {
    int student_id, roll_number, registration_number;
    String name, dob, enrollment_class, study_group, father_name, mother_name, sex, full_address, district, country;
}

Adapter Class:

public class RecyclerStudentAdapter extends RecyclerView.Adapter<RecyclerStudentAdapter.ViewHolder> {
    Context context;
    ArrayList<MyModel> data;

    public RecyclerStudentAdapter(Context context, ArrayList<MyModel> data)
    {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_info_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.student_id.setText(String.valueOf(data.get(position).student_id));
        holder.roll_number.setText(String.valueOf(data.get(position).roll_number));
        holder.registration_number.setText(String.valueOf(data.get(position).registration_number));
        holder.country.setText(data.get(position).country);
        holder.district.setText(data.get(position).district);
        holder.full_address.setText(data.get(position).full_address);
        holder.sex.setText(data.get(position).sex);
        holder.mother_name.setText(data.get(position).mother_name);
        holder.father_name.setText(data.get(position).father_name);
        holder.study_group.setText(data.get(position).study_group);
        holder.enrollment_class.setText(data.get(position).enrollment_class);
        holder.dob.setText(data.get(position).dob);
        holder.name.setText(data.get(position).name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView student_id, roll_number, registration_number, name, dob, enrollment_class,
                study_group, father_name, mother_name, sex, full_address, district, country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id = itemView.findViewById(R.id.student_id);
            roll_number = itemView.findViewById(R.id.roll_number);
            registration_number = itemView.findViewById(R.id.registration_number);
            name = itemView.findViewById(R.id.name);
            dob = itemView.findViewById(R.id.dob);
            enrollment_class = itemView.findViewById(R.id.enrollment_class);
            study_group = itemView.findViewById(R.id.study_group);
            father_name = itemView.findViewById(R.id.father_name);
            mother_name = itemView.findViewById(R.id.mother_name);
            sex = itemView.findViewById(R.id.sex);
            full_address = itemView.findViewById(R.id.full_address);
            district = itemView.findViewById(R.id.district);
            country = itemView.findViewById(R.id.country);
        }
    }
}

Main:

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MySQLiteHelper studentDB = new MySQLiteHelper(this);

        /*

        studentDB.insertData(1, 991, "Monir", "26-01-1983", "IV", "Humanities", "Wadud",
                "Jahanara", "Male", "Lalpur", "Natore", "Bangladesh");

        studentDB.insertData(2, 992, "Halim", "26-02-1983", "IV", "Science", "Nofil",
                "Ma", "Male", "Lalpur", "Natore", "Bangladesh");

        studentDB.insertData(3, 993, "Baschu", "26-03-1983", "IV", "Commerce", "Jamshed",
                "Ma", "Male", "Lalpur", "Natore", "Bangladesh");

        studentDB.insertData(4, 994, "Kibria", "26-04-1983", "IV", "Humanities", "Momotaj",
                "Ma", "Male", "Lalpur", "Natore", "Bangladesh");
         */

        ArrayList<MyModel> data = new ArrayList<>();
        data = studentDB.readData();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerStudentAdapter adapter = new RecyclerStudentAdapter(this, data);
        recyclerView.setAdapter(adapter);

        /*
        studentTable.deleteData(5);
        studentTable.deleteData(6);
        studentTable.deleteData(7);
        studentTable.deleteData(8);
        */

        /*
        studentTable.updateData(1);
        */

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

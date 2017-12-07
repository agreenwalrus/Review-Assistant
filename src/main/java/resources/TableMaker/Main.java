package resources.TableMaker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Student;
import data.UniversityClass;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
        SubGroup subGroup = new SubGroup();

        UniversityClass universityClass1 = new UniversityClass();
        universityClass1.setDate(dataFormat.parse("01.01.17 11:40"));
        universityClass1.setId(1);
        universityClass1.setSubGroup(subGroup);

        UniversityClass universityClass2 = new UniversityClass();
        universityClass2.setDate(dataFormat.parse("01.01.17 13:25"));
        universityClass2.setId(1);
        universityClass2.setSubGroup(subGroup);

        UniversityClass universityClass3 = new UniversityClass();
        universityClass3.setDate(dataFormat.parse("02.01.17 08:00"));
        universityClass3.setId(1);
        universityClass3.setSubGroup(subGroup);

        UniversityClass universityClass4 = new UniversityClass();
        universityClass4.setDate(dataFormat.parse("03.01.17 13:25"));
        universityClass4.setId(1);
        universityClass4.setSubGroup(subGroup);

        ArrayList<UniversityClass> universityClasses = new ArrayList<UniversityClass>();
        universityClasses.add(universityClass1);
        universityClasses.add(universityClass2);
        universityClasses.add(universityClass3);
        universityClasses.add(universityClass4);

        subGroup.setUniversityClassesList(universityClasses);

        Student student1 = new Student();
        student1.setId(1);
        student1.setFulName("Julia");
        student1.setSubGroup(subGroup);
        ArrayList<UniversityClass> universityClassesStudent1 = new ArrayList<UniversityClass>();
        universityClassesStudent1.add(universityClass1);
        universityClassesStudent1.add(universityClass4);
        student1.setMissedUniversityClassesList(universityClassesStudent1);

        Student student2 = new Student();
        student2.setId(2);
        student2.setFulName("Julia2");
        student2.setSubGroup(subGroup);
        ArrayList<UniversityClass> universityClassesStudent2 = new ArrayList<UniversityClass>();
        universityClassesStudent2.add(universityClass2);
        universityClassesStudent2.add(universityClass3);
        student1.setMissedUniversityClassesList(universityClassesStudent2);


        Student student3 = new Student();
        student3.setId(3);
        student3.setFulName("Toje Julia");
        student3.setSubGroup(subGroup);
        ArrayList<UniversityClass> universityClassesStudent3 = new ArrayList<UniversityClass>();
        universityClassesStudent3.add(universityClass1);
        student1.setMissedUniversityClassesList(universityClassesStudent3);

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        subGroup.setStudentsList(students);

        System.out.println(JsonMaker.getJsonSubGroupVisits(subGroup,true));

    }
}

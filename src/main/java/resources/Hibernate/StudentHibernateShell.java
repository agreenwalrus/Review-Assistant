package resources.Hibernate;

import data.Student;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentHibernateShell {

    static public void deleteStudent(String id) throws HibernateShellQueryException {
        Student student = HibernateShell.getStudentById(id);
        HibernateShell.delete(student);
    }

    static public void noteStudentPresence(String studentId,
                                           String classId) throws HibernateShellQueryException {
        HibernateShell.SQLQuery("DELETE FROM absentees WHERE id_class = " + classId + " and id_student = " + studentId);
    }

    static public void noteStudentAbsent(String stringId,
                                         String classId) throws HibernateShellQueryException {
        HibernateShell.SQLQuery("INSERT INTO absentees (id_class, id_student) VALUES ("+ classId + ", " + stringId + ");");
    }

    static public boolean insertStudent(String groupNumber, String subGroupNumber, String name, String eMail, String gitURL) throws HibernateShellQueryException {
        Student student = new Student();
        student.setFulName(name);
        student.seteMail(eMail);
        student.setGitURL(gitURL);
        student.setBonusMark(-1);

        Group group = HibernateShell.getGroupByGroupNumber(groupNumber);
        if(group == null)
            return false;

        SubGroup subGroup = group.getSubGroup(subGroupNumber);
        if(subGroup == null)
            return false;


        subGroup.addStudent(student);

        for(IssuedLab issuedLab : subGroup.getIssuedLabsList()) {
            LabMark labMark = new LabMark();
            labMark.setStudent(student);
            labMark.setIssuedLab(issuedLab);
            student.addLabMark(labMark);
        }
        List<Test> tests = HibernateShell.getTestKeeper().getTestList();
        for(Test test : tests){
            TestMark testMark = new TestMark();
            testMark.setStudent(student);
            testMark.setTest(test);

            student.addTestMark(testMark);
        }

        student.setSubGroup(subGroup);

        HibernateShell.save(student);
        return true;
    }

    static public boolean updateStudent(String id, String name, String eMail, String gitURL) throws HibernateShellQueryException {
        Student student = HibernateShell.getStudentById(id);

        if(student == null)
            return false;

        student.setFulName(name);
        student.seteMail(eMail);
        student.setGitURL(gitURL);

        HibernateShell.update(student);

        return true;
    }
}

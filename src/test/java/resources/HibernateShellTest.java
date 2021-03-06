package resources;

import data.group.Group;
import data.group.GroupsKeeper;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.lecturer.Lecturer;
import data.mark.LabMark;
import data.mark.TestMark;
import data.Student;
import data.UniversityClass;
import org.junit.Before;
import org.junit.Test;
import resources.Hibernate.HibernateShell;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class HibernateShellTest {
    private Group group;
    Lecturer lecturer;
    SubGroup subGroup1;
    UniversityClass universityClass;
    IssuedLab issuedLab;
    Lab lab;
    Student student;
    TestMark testMark;
    LabMark labMark;
    data.test.Test test;

    @Before
    public void setUp() throws Exception {
        group = new Group();
        lecturer = new Lecturer();
        subGroup1 = new SubGroup();
        universityClass = new UniversityClass();
        issuedLab = new IssuedLab();
        lab = new Lab();
        student = new Student();
        testMark = new TestMark();
        labMark = new LabMark();
        test = new data.test.Test();

        lecturer.setFullName("Иванов Иван Иваноыич");
        lecturer.setSubGroupList(Arrays.asList(subGroup1));

        group.setNumberOfGroup("550503");
        group.setScheduleApiGroupNumber("550503");
        group.setAmountOfTest(0);
        group.getSubGroupList().add(subGroup1);

        subGroup1.setLecturer(lecturer);
        subGroup1.setGroup(group);
        subGroup1.setUniversityClassesList(Arrays.asList(universityClass));
        subGroup1.setSubGroupNumber("1");
        subGroup1.setStudentsList(Arrays.asList(student));
        subGroup1.setIssuedLabsList(Arrays.asList(issuedLab));

        universityClass.setDate(new Date());
        universityClass.setSubGroup(subGroup1);

        issuedLab.setCoefficientOfCurrentDeadline(0.8);
        issuedLab.setCurrentDeadline(universityClass);
        issuedLab.setDateOfLastRepoCheck(new Date());
        issuedLab.setLabDescription(lab);
        issuedLab.setStudentControlList(Arrays.asList(student));
        issuedLab.setUniversityClassOfIssue(universityClass);

        lab.setKeyWord("lab");
        lab.setNumberOfLab(1);

        student.seteMail("student@gmail.com");
        student.setFulName("Петров Петр Петрович");
        student.setGitRepoName("studentRepo");
        student.setGitUserName("student");
        student.setBonusMark(9);
        student.setLabMarkList(Arrays.asList(labMark));
        student.setMissedUniversityClassesList(Arrays.asList(universityClass));
        student.setSubGroup(subGroup1);
        student.setTestMarkList(Arrays.asList(testMark));

        labMark.setCoefficient(0.2);
        labMark.setIssuedLab(issuedLab);
        labMark.setMark(9);
        labMark.setStudent(student);

        testMark.setMark(8);
        testMark.setStudent(student);
        testMark.setTest(test);

        test.setTestDate(new Date());
        test.setTestNumber(7);
        test.setTestMarkList(Arrays.asList(testMark));
    }


    @Test
    public void testAllHibernateShell() throws Exception {
        System.out.println("****************/test all HibernateShell/****************");

        GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();
        int groupSize = groupsKeeper.getGroupList().size();

        HibernateShell.save(group);
        groupsKeeper = HibernateShell.getGroupKeeper();
        assertEquals(groupSize + 1, groupsKeeper.getGroupList().size());
        assertEquals(group.getNumberOfGroup(),groupsKeeper.getGroupList().get(groupSize).getNumberOfGroup());
        assertEquals(group.getScheduleApiGroupNumber(),
                groupsKeeper.getGroupList().get(groupSize).getScheduleApiGroupNumber());

        System.out.println("test HibernateShell.save() & HibernateShell,getGroupKeeper() success.");


        lab = HibernateShell.getLabsKeeper().getLabList().get(0);
        lab.setNumberOfLab(2);
        HibernateShell.update(lab);
        assertEquals(lab.getNumberOfLab(),
                HibernateShell.getLabsKeeper().getLabList().get(0).getNumberOfLab());

        System.out.println("test HibernateShell.update() & HibernateShell,getLabKeeper() success.");

       /* assertTrue(HibernateShell.getNumberOfLab()==1);
        //assertTrue(HibernateShell.getNumberOfTests().intValue()==1);
        System.out.println("test HibernateShell.getNumberOfLab() & HibernateShell,getNumberOfTests() success.");*/
        HibernateShell.delete(group);

        groupsKeeper = HibernateShell.getGroupKeeper();
        assertEquals(groupSize,groupsKeeper.getGroupList().size());
        //assertTrue(HibernateShell.getGroupKeeper().getGroupList().isEmpty());
        System.out.println("test HibernateShell.delete() success.");

        HibernateShell.delete(lecturer);
        HibernateShell.delete(lab);
        test.setTestMarkList(null);
        HibernateShell.delete(test);
    }
}
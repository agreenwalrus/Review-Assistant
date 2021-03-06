package data.test;

import data.mark.TestMark;
import org.apache.log4j.Logger;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kesso on 19.08.17.
 */
@Entity
@Table(name = "tests")
public class Test {
    private static final Logger logger = Logger.getLogger(Test.class);
    @Id
    @Column(name = "id_test")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "test_number")
    private Integer testNumber;
    @Column(name = "test_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "test")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TestMark> testMarkList;

    public Test() {
        this.testMarkList = new ArrayList<TestMark>();
    }

    public Test(Integer id, Integer testNumber, Date testDate, List<TestMark> testMarkList) {
        this.id = id;
        this.testNumber = testNumber;
        this.testDate = testDate;
        this.testMarkList = testMarkList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(Integer testNumber) {
        this.testNumber = testNumber;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public List<TestMark> getTestMarkList() {
        return testMarkList;
    }

    public void setTestMarkList(List<TestMark> testMarkList) {
        this.testMarkList = testMarkList;
    }

    public void addTestMark(TestMark testMark) {
        logger.info("Add test mark(" + testMark.getStudent().getFulName() + ", " + testMark.getMark() + ") from test mark list(" + testNumber + ")");
        this.testMarkList.add(testMark);
    }
}

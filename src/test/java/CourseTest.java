import com.google.inject.Inject;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CourseDetailPage;
import pages.CoursesPage;
import pages.MainPage;

import java.io.IOException;

@ExtendWith(UIExtensions.class)
public class CourseTest {

  @Inject
  private MainPage mainPage;

  @Inject
  private CoursesPage coursesPage;

  @Inject
    private CourseDetailPage courseDetailPage;

    @Test
    public void openCardCourse() {
      coursesPage
       .open().clickCourseByName("Java QA Engineer. Basic");

      courseDetailPage
       .assertTitleCardCourse("Java QA Engineer. Basic");
    }

  @Test
  public void assertDateCourses() throws IOException {
    coursesPage.open()
     .assertTitleCardCourse(true);
  }
}

import java.util.Objects;

public class Screen {
    protected static void drawScreenHeader(String title, String subTitle) {
        System.out.print("\t\t\t\t\t\t\t\t\t   _______________________________________");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t" + title);
        if (!Objects.equals(subTitle, ""))
        {
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t   " + subTitle);
        }
        System.out.print("\n\t\t\t\t\t\t\t\t\t   _______________________________________\n\n");
    }
}

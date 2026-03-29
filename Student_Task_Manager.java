import java.util.*;

// Task Class
class Task {
    String title, description, deadline, priority;
    boolean isCompleted;

    Task(String title, String description, String deadline, String priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.isCompleted = false;
    }

    void markCompleted() {
        isCompleted = true;
    }

    void display(int index) {
        System.out.println("\nTask#" + index );
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Deadline: " + deadline);
        System.out.println("Priority: " + priority);
        System.out.println("Status: " + (isCompleted ? "Completed" : "Pending"));
    }
}

// Main Class
public class Main {

    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\nSTUDENT TASK MANAGEMENT SYSTEM");
            System.out.println("1.New Task");
            System.out.println("2.View Tasks");
            System.out.println("3.Mark Completed");
            System.out.println("4.Delete Task");
            System.out.println("5.Search Task");
            System.out.println("6.Sort by Priority");
            System.out.println("7.Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1: addTask(); break;
                case 2: viewTasks(); break;
                case 3: markCompleted(); break;
                case 4: deleteTask(); break;
                case 5: searchTask(); break;
                case 6: sortTasks(); break;
                case 7: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice.");
            }

        } while (choice != 7);
    }

    static void addTask() {
        System.out.print("Task Title:");
        String t = sc.nextLine();

        System.out.print("About task:");
        String d = sc.nextLine();

        System.out.print("Deadline: ");
        String dl = sc.nextLine();

        System.out.print("Priority (High/Medium/Low): ");
        String p = sc.nextLine();

        tasks.add(new Task(t, d, dl, p));
        System.out.println("Task added!");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Empty task list");
            return;
        }

        int i = 1;
        for (Task t : tasks) {
            t.display(i++);
        }
    }

    static void markCompleted() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Task number:");
        int n = sc.nextInt();

        if (n > 0 && n <= tasks.size()) {
            tasks.get(n - 1).markCompleted();
            System.out.println("Completed!");
        } else {
            System.out.println("Invalid!");
        }
    }

    static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Task number:");
        int n = sc.nextInt();

        if (n > 0 && n <= tasks.size()) {
            tasks.remove(n - 1);
            System.out.println("Deleted!");
        } else {
            System.out.println("Invalid!");
        }
    }

    static void searchTask() {
        System.out.print("Keyword:");
        String key = sc.nextLine().toLowerCase();

        boolean found = false;
        int i = 1;

        for (Task t : tasks) {
            if (t.title.toLowerCase().contains(key)) {
                t.display(i);
                found = true;
            }
            i++;
        }

        if (!found) System.out.println("No task found.");
    }

    static void sortTasks() {
        tasks.sort((a, b) -> priorityValue(a.priority) - priorityValue(b.priority));
        System.out.println("Sorted!");
    }

    static int priorityValue(String p) {
        if (p.equalsIgnoreCase("high")) return 1;
        if (p.equalsIgnoreCase("medium")) return 2;
        if (p.equalsIgnoreCase("low")) return 3;
        return 4;
    }
}
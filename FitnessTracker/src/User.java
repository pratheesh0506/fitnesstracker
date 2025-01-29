public class User {
    private String username;
    private double height;
    private double weight;
    private int age;
    private String gender;

    public User(String username, double height, double weight, int age, String gender) {
        this.username = username;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }

    // Getters and setters for user attributes
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

package animals;

public class Animal {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    private String gender;


    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }
private void setName(String name) {
        if (name.equals("")){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name;
    }

    private void setGender(String gender) {
        if (name.equals("")){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.gender = gender;
    }

    private void setAge(int age) {
        if (age < 0){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }
    public String produceSound(){
        return "";
    }

    @Override
    public String toString() {
       return String.format("%s%n%s %d %s%n%s",
               this.getClass().getSimpleName(),name,age,gender,produceSound());
    }
}

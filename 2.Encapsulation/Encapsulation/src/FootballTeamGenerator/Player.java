package FootballTeamGenerator;

public class Player {
     private String name;
     private int endurance;
     private int sprint;
     private int dribble;
     private int passing;
     private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 1){
            System.out.println("A name should not be empty.");
        }
        this.name = name;
    }

    private void setEndurance(int endurance) {
        if (!validator(endurance)){
           throw new IllegalArgumentException("Endurance should be between 0 and 100.");

        }
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        if (!validator(sprint)){
            System.out.println("Sprint should be between 0 and 100.");
        }
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        if (!validator(dribble)){
            System.out.println("Dribble should be between 0 and 100.");
        }
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        if (!validator(passing)){
            System.out.println("Passing should be between 0 and 100.");
        }
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        if (!validator(shooting)){
            System.out.println("Shooting should be between 0 and 100.");
        }
        this.shooting = shooting;
    }

    public String getName() {
        return name;
    }
    private boolean validator(int stats){
          return stats >= 0 && stats <= 100;
    }
    public double overallSkillLevel(){
        return (1.0 * (endurance + sprint + dribble + passing + shooting)) / 5.0;
    }
}

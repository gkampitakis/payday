package model.position;

public abstract class Position {

    private int positionNumb;
    private final String imageURL;
    private String name;

    /**
     * <b>constructor</b>:Constructs a new Position
     *
     * @param imageURL the image's url
     * @param positionNumb the number of the positon
     */
    public Position(String imageURL, int positionNumb) {
        this.imageURL = imageURL;
        this.positionNumb = positionNumb;
    }

    /**
     * <b>Accessor</b>
     *
     * @return positionNumb the number of the position
     */
    public int getpositionNumb() {
        return positionNumb;
    }
    /**
     * <b>Transformer<b>
     * @param x sets the number of the position
     */

    public void setpositionNumb(int x) {
        positionNumb = x;
    }
    /**
     * <b>Observer<b>
     * @return String the day of the position 
     */
    public String getName() {
        return name;
    }
    /**
     * <b>Transformer<b>  sets the day of the position
     * @param name holds the day of the position
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <b>Accessor</b>:
     * @return true if its Sunday or false else
     */
    public boolean isSunday() {
        return name.equals("Sunday");
    }
    /**
     * <b>Accessor<b>
     * @return the path of the position's image
     */
    public String getImage() {
        return imageURL;
    }
}

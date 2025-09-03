package main.java.house.shapes;

public class Cloud {
    private final Circle[] circles = new Circle[3];
    private int size;
    private int xPosition;
    private int yPosition;
    private String color;

    /**
     * Create a new cloud at default position with default color.
     */
    public Cloud()
    {
        size = 68;
        xPosition = 230;
        yPosition = 90;
        color = "white";
        initializeCloudShapes();
    }

    /**
     * Create a new cloud at with all args.
     */
    public Cloud(int size, int xPosition, int yPosition, String color) {
        this.size = size;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        initializeCloudShapes();
    }

    /**
     * Initialize the clouds part shapes.
     */
    private void initializeCloudShapes()
    {
        circles[0] = new Circle((int)(size * 0.735), (int)(xPosition - size*0.3), (int)(yPosition + (size * 0.25)) , color);
        circles[1] = new Circle(size, xPosition, yPosition, color);
        circles[2] = new Circle((int)(size * 0.735), (int)(xPosition + size - size*0.7 + size*0.3), (int)(yPosition + (size * 0.25)), color);
    }

    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        for (Circle circle : circles) circle.makeVisible();
    }

    /**
     * Make this circle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        for (Circle circle : circles) circle.makeInvisible();
    }

    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Move the circle horizontally by 'distance' pixels.
     */
    public void moveHorizontal(int distance)
    {
        xPosition += distance;
        for (Circle circle : circles) circle.moveHorizontal(distance);
    }

    /**
     * Move the circle vertically by 'distance' pixels.
     */
    public void moveVertical(int distance)
    {
        yPosition += distance;
        for (Circle circle : circles) circle.moveVertical(distance);
    }

    /**
     * Slowly move the circle horizontally by 'distance' pixels.
     */
    public void slowMoveHorizontal(int distance)
    {
        int delta;

        if(distance < 0)
        {
            delta = -1;
            distance = -distance;
        }
        else
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            xPosition += delta;
            for (Circle circle : circles) circle.moveHorizontal(delta);
        }
    }

    /**
     * Slowly move the circle vertically by 'distance' pixels.
     */
    public void slowMoveVertical(int distance)
    {
        int delta;

        if(distance < 0)
        {
            delta = -1;
            distance = -distance;
        }
        else
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            yPosition += delta;
            for (Circle circle : circles) circle.moveVertical(delta);
        }
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newSize)
    {
        size = newSize;

        circles[0].changeSize(newSize / 2);
        circles[1].changeSize(newSize);
        circles[2].changeSize(newSize / 2);

        circles[0].moveVertical(newSize / 2);
        circles[2].moveVertical(newSize / 2);
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        for (Circle circle : circles) circle.changeColor(newColor);
    }
}

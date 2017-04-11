# Java Cheatsheet

Based on examples from [Java in 21 minutes](http://fileadmin.cs.lth.se/cs/Education/EDA040/common/java21.pdf), the following


## Simple declarations
```java
int m, n; // Two integer variables
double x, y; // Two real coordinates
boolean b; // Either ‘true’ or ‘false’
char ch; // A character, such as ‘P’ or ‘@’
```

## Numeric expressions and assignments
```java
n = 3 * (5 + 2);
x = y / 3.141592653;
n = m % 8; // Modulo, i.e. n is now (m mod 8)
b = true;
ch = ‘x‘;

double f = 0.57;
boolean flag = true;
```

## Type conversion (casting)
```java
double radians;
int degrees;
...
degrees = radians * 180 / 3.141592653; // Error
degrees = (int) (radians * 180 / 3.141592653); // OK
```

# Statements

## If statements and boolean expressions
```java
// conditional operators are ‘<‘, ‘>’, ‘<=’, ‘>=’, '!='
if (n == 3)
 x = 3.2;
```

```java
// conditional if with only one statement need no code block
if (x != 0)
 y = 3.0 / x; // Executed when x is non-zero
else
 y = 1; // Executed when x is zero
```

```java
if (x != 0) {
 y = 3.0 / x;
 x = x + 1;
} else // <--- Note: no semicolon
 y = 1;
```


```java
// Boolean expressions
// and operator &&
// or operator ||
// not operator !
int x, y;
boolean b;
...
if ((x <= 9 || y > 3) && !b) {
 b = true;
}
```

## While and for statements

```java
// Calculate exp(1). End when the term is less than 0.00001
double sum = 0.0;
double term = 1.0;
int k = 1;
while (term >= 0.00001) {
 sum = sum + term;
 term = term / k;
 k++; // Shortcut for ‘k = k + 1’
}
```

```java
// Calculate 1 + (1/2) + (1/3) + ... + (1/100)
int i;
double sum = 0.0;
for (i = 1; i <= 100; i++) {
 sum = sum + 1.0 / i;
}
```

```java
// there are at least four ways to increment an integer variable1:
i = i + 1;
i++;
++i;
i += 1;
// -- and -=
```

# Classes and objects

## Classes
```java
class Turtle {
private boolean penDown;
protected int x, y;
// Declare some more stuff
}
```

## Methods

```java
class Turtle {
// Attribute declarations, as above
public void jumpTo(int newX, int newY) {
 x = newX;
 y = newY;
 }
public int getX() {
return x;
 }
}
```


## Using objects
```java
Turtle t;
t = new Turtle(100, 100);
int a = t.getX();
t.jumpTo(300, 200);
```

## Parameters to classes: constructors
```java 
public Turtle(int initX, int initY) {
 x = initX;
 y = initY;
 penDown = false;
}
```

## The main method
```java
public static void main(String[] args) {
 Turtle t = new Turtle(100, 200);
 t.right(90);
while (t.getX() < 150) {
 t.forward(2);
 }
}
```

## Inheritance
```java
class NinjaTurtle extends Turtle {
// Declarations for Ninja turtles
}
```

```java
public NinjaTurtle(int initX, int initY, String name) {
super(initX, initY); // Call superclass’ constructor
// ... do some more initialization stuff...
}
```

## Interfaces and listeners

```java
interface MouseListener {
void processMouseClick(int x, int y);
}
```

```java
class SomeClass extends SomeOtherClass implements MouseListener {
// ...declarations...
public void processMouseClick(int x, int y) {
// Do something sensible here
 }
}
```

```java
class WindowSystem {
public void addMouseListener(MouseListener m) {
// Insert m into some clever data structure
 }
// ... and loads of more stuff...
}
```

# Exceptions

## Catching exceptions
```java
int m, n;
try {
 n = getNatural();
 m = n * 2; // If an exception is thrown, this is not executed
}
catch (IOException e) {
// The user entered something wrong. Use 1 as default.
 n = 1;
 m = 2;
}
```

```java
public void doStuff() throws IOException {
int n = getNatural(); // May throw an exception
// Clever calculations using n...
}
```

## Throwing exceptions
```java
public int getNatural() throws IOException {
char ch;
while (more input) {
 ch = (read character);
if (ch < ‘0’ || ch > ‘9’) {
throw new IOException(“bad natural number”);
 }
 ...
 }
 ...
}
```

## Declaring new exceptions
```java
class OverheatedException extends Exception {
public OverheatedException(String s, double temp) {
super(s);
 myTemperature = temp;
 }
public double getTemperature() {
return myTemperature;
 }
private double myTemperature;
}
```

## Using packages
```java
import java.awt.*;
```

## Arrays
```java
someInts = new int[30];
turtleFarm = new Turtle[100];

int i;
for (i = 0; i < someInts.length; i = i + 1) {
 someInts[i] = i * i;
}
```

## Writing to the terminal
```java
System.out.print(“Jag vill bo “);
System.out.println(“i en svamp“);
System.out.println(“Annars får jag kramp“);
// The resulting output is:
Jag vill bo i en svamp
Annars får jag kramp
```
```java
int a;
a = 6 * 7;
System.out.println(“6 * 7 = “ + a);
```

# A complete Java program

```java
/**
 * The following example program displays a window with graphical figures (squares and
 * circles). The program is not intended to be useful in any way except as an example of a
 * complete Java program.
 *
 * Figure.java
 */
import java.awt.*;

/**
 * Simple abstract class for graphic figures that can be drawn in windows.
 */
abstract class Figure {
	/**
	 * Constructor: takes two parameters, the X and Y coordinates.
	 */
	public Figure(int inX, int inY) {
		x = inX;
		y = inY;
	}

	/**
	 * Abstract method for drawing this thing.
	 *
	 * The g parameter is a ’pen’ that can be used to draw things in the window.
	 */
	public abstract void draw(Graphics g);

	/**
	 * Move the figure to (newX, newY).
	 */
	public void move(int newX, int newY) {
		x = newX;
		y = newY;
	}

	protected int x, y; // X and Y coordinates
}
```

```java
/**
 * A square that can be drawn in a window. The coordinates represent the upper
 * left corner of the square.
 */
class Square extends Figure {
	/**
	 * Constructor: first two parameters are the coordinates, the third is the
	 * side.
	 */
	public Square(int inX, int inY, int inSide) {
		super(inX, inY);
		side = inSide;
	}

	/**
	 * Drawing method for squares.
	 */
	public void draw(Graphics g) {
		g.drawRect(x, y, side, side);
	}

	private int side; // Square side
}
```

```java
import java.awt.*;

/**
 * Circle class. The coordinates represent the circle’s center.
 */
class Circle extends Figure {
	/**
	 * Constructor: the first two parameters are the coordinates, the third is
	 * the diameter.
	 */
	public Circle(int inX, int inY, int inDiam) {
super(inX, inY);
d = inDiam;
Teach Yourself Java in 21 Minutes 17
 }

	/**
	 * Drawing method for circles.
	 */
	public void draw(Graphics g) {
		g.drawOval(x, y, d, d);
	}

	private int d; // Circle diameter
}
```


```java
import java.awt.*;

/**
 * A simple window to display graphic figures in. The window is a subclass of
 * the Java ’Frame’ class, which describes graphic windows. The window keeps its
 * figures in an array.
 *
 * The Java window system (AWT) automatically calls the paint method in the
 * Frame class whenever the window’s contents need to be redrawn. A new
 * implementation of paint is provided in FigureWindow to handle the drawing.
 */
class FigureWindow extends Frame {
	/**
	 * Constructor: the parameter indicates the maximal number of figures.
	 */
	public FigureWindow(int max) {
		super("Fabulous Figures"); // Window title
		figures = new Figure[max];
		nbrOfFigures = 0;
	}

	/**
	 * Add the figure f to the window. If the maximal number of figures has been
	 * reached, nothing happens.
	 */
	public void addFigure(Figure f) {
		if (nbrOfFigures < figures.length) {
			figures[nbrOfFigures] = f;
			nbrOfFigures++;
		}
	}

	/**
	 * This method is called automatically by the system. Draws the raphic
	 * figures associated with the window.
	 *
	 * The g parameter is a drawing ’pen’ provided by the system.
	 */
	public void paint(Graphics g) {
		int i;
		for (i = 0; i < nbrOfFigures; i++) {
			figures[i].draw(g);
		}
	}

	// Array of graphic figures
	private Figure[] figures;
	// Current number of figures
	private int nbrOfFigures;

	/**
	 * Main method: creates a FigureWindow and a few figures inside it.
	 */
	public static void main(String[] args) {
		FigureWindow w = new FigureWindow(10);
		w.setSize(400, 300);
		w.addFigure(new Square(50, 50, 200));
		w.addFigure(new Circle(200, 100, 150));
		w.addFigure(new Circle(300, 200, 200));
		w.show();
	}
}
```


https://github.com/BafS/Java8-CheatSheet
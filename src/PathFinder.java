
import java.util.*;

public class PathFinder {

    Vertex[][] graph;
    Vertex source;
    Vertex destination;

    ArrayList<Vertex> euclidean(boolean[][] matrix, int si, int sj, int ei, int ej) {

        // Setting the Horizontal and Vertical Distances
        double hvDistance = 1.0;

        // Setting the Diagonal Distance
        double diagonalDistance = 1.4;

        int size = matrix.length;

        // Vertices are created for the start and end
        source = new Vertex(si, sj);
        destination = new Vertex(ei, ej);

        // The grid that is used to store nodes
        graph = new Vertex[size][size];

        // Setting the blocked boolean variable of the Vertex object
        // Two for loops are used to traverse in the array
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                graph[i][j] = new Vertex(i, j);
                if (matrix[i][j] == false) {
                    graph[i][j].blocked = true;
                }
            }
        }

        // In the definition of the Vertex class, the distance is set to Integer.MAX_VALUE
        // We manually set the distance value of the source Vertex as 0
        source.distance = 0;

        // Comparator for the Queue we're about to use
        Comparator<Vertex> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        // This queue will store the nodes we visit
        Queue<Vertex> queue = new PriorityQueue(size, adjacencyComparator);
        
        queue.add(source);

        while (queue.size() > 0) {
            Vertex current = queue.remove();
            Vertex temp;

            // Nodes above the current node are checked
            if (current.x - 1 >= 0) {

                // Horizontally up node
                temp = graph[current.x - 1][current.y];
                if (!temp.visited && !temp.blocked && temp.distance > current.distance + hvDistance) {
                    temp.distance = current.distance + hvDistance;
                    temp.parent = current;
                    queue.add(temp);
                }

                // Diagonally up-left node
                if (current.y - 1 > 0) {
                    temp = graph[current.x - 1][current.y - 1];
                    if (!temp.visited && !temp.blocked && temp.distance > current.distance + diagonalDistance) {
                        temp.distance = current.distance + diagonalDistance;
                        temp.parent = current;
                        queue.add(temp);
                    }
                }

                // Diagonally up-right node
                if (current.y + 1 < size) {
                    temp = graph[current.x - 1][current.y + 1];
                    if (!temp.visited && !temp.blocked && temp.distance > current.distance + diagonalDistance) {
                        temp.distance = current.distance + diagonalDistance;
                        temp.parent = current;
                        queue.add(temp);
                    }
                }
            }

            // Node to the left
            if (current.y - 1 > 0) {
                temp = graph[current.x][current.y - 1];
                if (!temp.visited && !temp.blocked && temp.distance > current.distance + hvDistance) {
                    temp.distance = current.distance + hvDistance;
                    temp.parent = current;
                    queue.add(temp);
                }
            }

            // Node to the right
            if (current.y + 1 < size) {
                temp = graph[current.x][current.y + 1];
                if (!temp.visited && !temp.blocked && temp.distance > current.distance + hvDistance) {
                    temp.distance = current.distance + hvDistance;
                    temp.parent = current;
                    queue.add(temp);
                }
            }
            // Nodes below the current node are checked as following
            if (current.x + 1 < size) {
                
                temp = graph[current.x + 1][current.y];

                // Horizontally bottom most node
                if (!temp.visited && !temp.blocked && temp.distance > current.distance + hvDistance) {
                    temp.distance = current.distance + hvDistance;
                    temp.parent = current;
                    queue.add(temp);
                }

                // Diagonally down left
                if (current.y - 1 >= 0) {
                    temp = graph[current.x + 1][current.y - 1];
                    if (!temp.visited && !temp.blocked && temp.distance > current.distance + diagonalDistance) {
                        temp.distance = current.distance + diagonalDistance;
                        temp.parent = current;
                        queue.add(temp);
                    }
                }

                // Diagonally down right
                if (current.y + 1 < size) {
                    temp = graph[current.x + 1][current.y + 1];
                    if (!temp.visited && !temp.blocked && temp.distance > current.distance + diagonalDistance) {
                        temp.distance = current.distance + diagonalDistance;
                        temp.parent = current;
                        queue.add(temp);
                    }
                }
            }
            
            // After checking all neibour nodes the visited boolean of the current node is set to true
            current.visited = true;
        }
        
        System.out.println("Total Cost: " + graph[destination.x][destination.y].distance);

        ArrayList<Vertex> path = new ArrayList<>();

        // If the path is possible
        if (!(graph[destination.x][destination.y].distance == Integer.MAX_VALUE)) {
            Vertex current = graph[destination.x][destination.y];

            while (current.parent != null) {
                path.add(current.parent);
                current = current.parent;
            }
        } else {
            System.out.println("No possible path");
        }

        return path;
    }

    ArrayList<Vertex> manhatten(boolean[][] matrix, int si, int sj, int ei, int ej) {

        // Horizontal and Vertical Distance
        double hvDistance = 1.0;

        int size = matrix.length;

        source = new Vertex(si, sj);
        destination = new Vertex(ei, ej);
        // The grid that is used to store nodes
        graph = new Vertex[size][size];

        // Creating nodes and finding blocked cells in matrix and mapping accordingly to our grid
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                graph[i][j] = new Vertex(i, j);
                if (matrix[i][j] == false) {
                    graph[i][j].blocked = true;
                }
            }
        }

        source.distance = 0;

        Comparator<Vertex> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        Queue<Vertex> queue = new PriorityQueue(size, adjacencyComparator);

        queue.add(source);

        while (queue.size() > 0) {
            Vertex current = queue.remove();
            Vertex t;

            if (current.x - 1 >= 0) {

                t = graph[current.x - 1][current.y];
                if (!t.visited && !t.blocked && t.distance > current.distance + hvDistance) {
                    t.distance = current.distance + hvDistance;
                    t.parent = current;
                    queue.add(t);
                }
            }

            if (current.y - 1 > 0) {
                t = graph[current.x][current.y - 1];
                if (!t.visited && !t.blocked && t.distance > current.distance + hvDistance) {
                    t.distance = current.distance + hvDistance;
                    t.parent = current;
                    queue.add(t);
                }
            }

            if (current.y + 1 < size) {
                t = graph[current.x][current.y + 1];
                if (!t.visited && !t.blocked && t.distance > current.distance + hvDistance) {
                    t.distance = current.distance + hvDistance;
                    t.parent = current;
                    queue.add(t);
                }
            }
            
            if (current.x + 1 < size) {

                // Down Down
                t = graph[current.x + 1][current.y];
                if (!t.visited && !t.blocked && t.distance > current.distance + hvDistance) {
                    t.distance = current.distance + hvDistance;
                    t.parent = current;
                    queue.add(t);
                }
            }
            current.visited = true;
        }
        
        System.out.println("Total Cost: " + graph[destination.x][destination.y].distance);

        ArrayList<Vertex> path = new ArrayList<>();

        if (!(graph[destination.x][destination.y].distance == Integer.MAX_VALUE)) {
            Vertex current = graph[destination.x][destination.y];

            while (current.parent != null) {
                path.add(current.parent);
                current = current.parent;
            }
        } else {
            System.out.println("The destination node cannot be reached fromt the source!");
        }

        return path;
    }
    
        ArrayList<Vertex> chebyshev(boolean[][] matrix, int si, int sj, int ei, int ej) {

        double hvDistance = 1.0;
        double diagonalDistance = 1.0;

        int size = matrix.length;

        source = new Vertex(si, sj);
        destination = new Vertex(ei, ej);

        graph = new Vertex[size][size];

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                graph[i][j] = new Vertex(i, j);
                if (matrix[i][j] == false) {
                    graph[i][j].blocked = true;
                }
            }
        }

        source.distance = 0;

        Comparator<Vertex> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        Queue<Vertex> queue = new PriorityQueue(size, adjacencyComparator);
        
        queue.add(source);

        while (queue.size() > 0) {
            Vertex current = queue.remove();
            Vertex temp;

            if (current.x - 1 >= 0) {

                temp = graph[current.x - 1][current.y];
                if (!temp.visited && !temp.blocked && temp.distance > current.distance + hvDistance) {
                    temp.distance = current.distance + hvDistance;
                    temp.parent = current;
                    queue.add(temp);
                }

                if (current.y - 1 > 0) {
                    temp = graph[current.x - 1][current.y - 1];
                    if (!temp.visited && !temp.blocked && temp.distance > current.distance + diagonalDistance) {
                        temp.distance = current.distance + diagonalDistance;
                        temp.parent = current;
                        queue.add(temp);
                    }
                }

                if (current.y + 1 < size) {
                    temp = graph[current.x - 1][current.y + 1];
                    if (!temp.visited && !temp.blocked && temp.distance > current.distance + diagonalDistance) {
                        temp.distance = current.distance + diagonalDistance;
                        temp.parent = current;
                        queue.add(temp);
                    }
                }
            }

            if (current.y - 1 > 0) {
                temp = graph[current.x][current.y - 1];
                if (!temp.visited && !temp.blocked && temp.distance > current.distance + hvDistance) {
                    temp.distance = current.distance + hvDistance;
                    temp.parent = current;
                    queue.add(temp);
                }
            }

            if (current.y + 1 < size) {
                temp = graph[current.x][current.y + 1];
                if (!temp.visited && !temp.blocked && temp.distance > current.distance + hvDistance) {
                    temp.distance = current.distance + hvDistance;
                    temp.parent = current;
                    queue.add(temp);
                }
            }
            if (current.x + 1 < size) {
                
                temp = graph[current.x + 1][current.y];

                if (!temp.visited && !temp.blocked && temp.distance > current.distance + hvDistance) {
                    temp.distance = current.distance + hvDistance;
                    temp.parent = current;
                    queue.add(temp);
                }

                if (current.y - 1 >= 0) {
                    temp = graph[current.x + 1][current.y - 1];
                    if (!temp.visited && !temp.blocked && temp.distance > current.distance + diagonalDistance) {
                        temp.distance = current.distance + diagonalDistance;
                        temp.parent = current;
                        queue.add(temp);
                    }
                }

                if (current.y + 1 < size) {
                    temp = graph[current.x + 1][current.y + 1];
                    if (!temp.visited && !temp.blocked && temp.distance > current.distance + diagonalDistance) {
                        temp.distance = current.distance + diagonalDistance;
                        temp.parent = current;
                        queue.add(temp);
                    }
                }
            }
            
            current.visited = true;
        }
        
        System.out.println("Total Cost: " + graph[destination.x][destination.y].distance);

        ArrayList<Vertex> path = new ArrayList<>();

        if (!(graph[destination.x][destination.y].distance == Integer.MAX_VALUE)) {
            Vertex current = graph[destination.x][destination.y];

            while (current.parent != null) {
                path.add(current.parent);
                current = current.parent;
            }
        } else {
            System.out.println("The destination node cannot be reached fromt the source!");
        }

        return path;
    }

    class Vertex {

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
        double distance = Integer.MAX_VALUE;
        Vertex parent = null;
        boolean visited;
        boolean blocked;

    }

    // The method used to generate the random boolean array with the desired proportion of blocked cells
    public static boolean[][] random(int N, double p) {
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = StdRandom.bernoulli(p);
            }
        }
        return a;
    }

    // This method is given in the problem, it draws the path and shows the start and end points
    // In my solutions I haven't used this method, the path is shown in a more efficient way
    public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] == which) {
                    if ((i == x1 && j == y1) || (i == x2 && j == y2)) {
                        StdDraw.circle(j, N - i - 1, .5);
                    } else {
                        StdDraw.square(j, N - i - 1, .5);
                    }
                } else {
                    StdDraw.filledSquare(j, N - i - 1, .5);
                }
            }
        }
    }

    // This method is given in the problem, it draws the path
    public static void show(boolean[][] a, boolean which) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] == which) {
                    StdDraw.square(j, N - i - 1, .5);
                } else {
                    StdDraw.filledSquare(j, N - i - 1, .5);
                }
            }
        }
    }

    public static void main(String[] args) {

        // Following method call will generate a 2D boolean array 
        // The second parameter indicates the proportion of the open cells 
        // Lower the seocond paramemter, higher the blocked cells
        boolean[][] randomlyGenMatrix = random(10, 0.6);

        // print the boolean array in the console and display the graphical matrix
        StdArrayIO.print(randomlyGenMatrix);
        show(randomlyGenMatrix, true);
        System.out.println();

        // initialising the four variables for the cordinates
        int Ai, Aj, Bi, Bj;

        // Getting the input cordinates from the user
//    	Scanner in = new Scanner(System.in);
//        System.out.println("Enter i for A > ");
//        Ai = in.nextInt();
//        System.out.println("Enter j for A > ");
//        Aj = in.nextInt();
//        System.out.println("Enter i for B > ");
//        Bi = in.nextInt();
//        System.out.println("Enter j for B > ");
//        Bj = in.nextInt();
        
        // Generating two cordinates randomly
        Random random = new Random();
        do {
            Ai = random.nextInt(randomlyGenMatrix.length - 1 + 1);
            Aj = random.nextInt(randomlyGenMatrix.length - 1 + 1);
            Bi = random.nextInt(randomlyGenMatrix.length - 1 + 1);
            Bj = random.nextInt(randomlyGenMatrix.length - 1 + 1);
        } while /*checks whether the randomly generated cells are blocked*/ (!(randomlyGenMatrix[Ai][Aj] == true && randomlyGenMatrix[Bi][Bj] == true));

        // Prints the cordinates to the console
        System.out.println("i = " + Ai + "," + Aj);
        System.out.println("j = " + Bi + "," + Bj);
        System.out.println("");
        
        // Starts the Java in-built stopwatch
        Stopwatch timerFlow = new Stopwatch();

        // An ArrayList of Vertices is created called shortestPath
        // The desired calculation matric can be used here (Eucledian, Cherbyshev, Manhatten)
        // The generated random matrix and the coordinates are given to the method as parameters
        ArrayList<PathFinder.Vertex> shortestPath = new PathFinder().euclidean(randomlyGenMatrix, Ai, Aj, Bi, Bj);

        // The elapsed time is displayed in the console
        StdOut.println("Elapsed time = " + timerFlow.elapsedTime());

        // Draw the calculated path in the StdDraw output
        StdDraw.setPenColor(StdDraw.GREEN);
        for (PathFinder.Vertex node : shortestPath) {
            StdDraw.filledSquare(node.y, randomlyGenMatrix.length - node.x - 1, 0.5);
        }
        //Patches 1

        // Show the start and end points in the graphical matrix in StdOut
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(Aj, randomlyGenMatrix.length - Ai - 1, "S");
        StdDraw.text(Bj, randomlyGenMatrix.length - Bi - 1, "E");
    }
}

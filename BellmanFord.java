import java.util.*;

public class BellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the no of vertices: ");
        int n = sc.nextInt();

        System.out.println("Enter the weight matrix of the graph (use 0 for no edge):");
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = sc.nextInt();

        System.out.print("Enter the source vertex: ");
        int source = sc.nextInt();

        BellmanFord bf = new BellmanFord();
        bf.bellmanFord(graph, source - 1);
        sc.close();
    }

    public void bellmanFord(int[][] graph, int source) {
        int n = graph.length;
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                            dist[u] + graph[u][v] < dist[v])
                        dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Negative weight cycle detected.");
                    return;
                }
            }
        }

        System.out.println("\nVertex\tDistance from source");
        for (int i = 0; i < n; i++)
            System.out.println((i + 1) + "\t\t" + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
    }
}

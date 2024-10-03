import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// LC 207
public class CourseSchedule {
    /**
     * Similar to detect cycle in a directed graph using Kahn's algo (Topo sort BFS)
     * <p>
     * If we are able to generate a topo sort of numCourses --> no cycle detected --> can finish all courses.
     * else, not
     * <p>
     * TC: O(V + E)
     * SC: O(V)
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        Deque<Integer> queue = new ArrayDeque<>();
        // find in-degrees
        int[] inDegrees = new int[numCourses];
        for (int[] i : prerequisites) {
            adjList.get(i[1]).add(i[0]);
            inDegrees[i[0]]++;
        }

        // consider inDegrees with 0 as 'u' == source nodes
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int i = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            i++;
            // remove u from queue, i.e, remove its edges --> reduce inDegrees by 1 for its adjacent nodes
            for (int j : adjList.get(curr)) {
                inDegrees[j]--;
                if (inDegrees[j] == 0) {
                    queue.offer(j);
                }
            }
        }
        return i == numCourses;
    }
}

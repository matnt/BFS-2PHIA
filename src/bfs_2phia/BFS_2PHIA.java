/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs_2phia;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Mat Nguyen
 */
public class BFS_2PHIA {
    int n;
    int m;
    Set<Integer> V;
    Map<Integer, Set<Integer>> A;
    Queue<Integer> Q;
    int[] p;
    char[] color;
    int[] level;
    
    public BFS_2PHIA(){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BFS_2PHIA bfs2 = new BFS_2PHIA();
        bfs2.input();
        bfs2.init();
        bfs2.resolve();
    }
    
    public void input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        
        V = new HashSet<>();
        A = new HashMap<Integer, Set<Integer>>();
        Q = new LinkedList<Integer>();
        color = new char[n + 1];
        level = new int[n + 1];
        p = new int[n + 1];
        for(int i = 0; i < n; i++){
            int v = in.nextInt();
            V.add(v);
            A.put(v, new HashSet<Integer>());
            
        }
        for(int i = 0; i < m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            A.get(u).add(v);
            A.get(v).add(u);
        }
    }
    
    public void init(){
        for(int v: V){
            color[v] = '-';
            p[v] = -1;
        }
    }
    
    public void resolve(){
        boolean result = BFS_visit(1);
        if(result)
            System.out.println("Đồ thị 2 phía");
        else
            System.out.println("Không phải là đồ thị 2 phía");
        
    }
    
    public boolean BFS_visit(int s){
        Q.add(s);
        p[s] = 0;
        level[s] = 0;
        color[s] = 'W';
        while(Q.size() > 0){
            int u = Q.poll();
            for(int v: A.get(u)){
                if(color[u] == color[v])
                    return false;
                else if(color[v] == '-'){
                    p[v] = u;
                    level[v] = level[u] + 1;
                    if(level[v] % 2 == 0)
                        color[v] = 'W';
                    else 
                        color[v] = 'B';
                    Q.add(v);
                }
                    
            }
        }
        
        return true;
    }
}

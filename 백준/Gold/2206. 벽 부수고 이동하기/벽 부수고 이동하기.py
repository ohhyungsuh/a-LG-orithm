# from sys import stdin
# from collections import deque

# dx = [-1, 0, 1, 0]
# dy = [0, 1, 0, -1]


# def bfs(i, j, Map):
#     q = deque()
#     q.append((i, j, 1))
    
#     while q:
#         x, y, cnt = q.popleft()
#         visited[x][y] = True
#         for i in range(4):
#             nx = x + dx[i]
#             ny = y + dy[i]
#             ncnt = cnt + 1
            
#             if nx < 0 or nx >= n or ny < 0 or ny >= m:
#                 continue
#             elif nx == n-1 and ny == m-1:
#                 return ncnt
#             else:
#                 if Map[nx][ny] == 0 and not visited[nx][ny]:
#                     q.append((nx, ny, ncnt))
    
#     return -1

# n, m = map(int, stdin.readline().split())
# Map = []
# result = 2147483647
                    
# for _ in range(n):
#     Map.append(list(map(int, stdin.readline().strip())))

# for i in range(n):
#     for j in range(m):
#         if Map[i][j] == 1:
#             visited = [[False]*m for _ in range(n)]
#             Map[i][j] = 0
#             distance = bfs(0, 0, Map)
#             Map[i][j] = 1
#             if distance == -1:
#                 continue
#             else:
#                 result = distance if distance <= result else result
            
# print(-1 if result == 2147483647 else result)

from collections import deque
from sys import stdin
n, m = map(int, stdin.readline().split())
Map = [list(map(int, stdin.readline().rstrip())) for _ in range(n)]
visited = [[[0] * 2 for _ in range(m)] for _ in range(n)]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def bfs(x, y, Map):
    q = deque()
    q.append((x, y, 0))
    visited[x][y][0] = 1
    
    while q:
        x, y, crash = q.popleft()
        if x == n-1 and y == m-1:
            return visited[x][y][crash]
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            
            if Map[nx][ny] == 0 and visited[nx][ny][crash] == 0:
                q.append((nx, ny, crash))
                visited[nx][ny][crash] = visited[x][y][crash] + 1
            
            if Map[nx][ny] == 1 and crash == 0:
                q.append((nx, ny, crash+1))
                visited[nx][ny][crash + 1] = visited[x][y][crash] + 1
    return -1

print(bfs(0, 0, Map))
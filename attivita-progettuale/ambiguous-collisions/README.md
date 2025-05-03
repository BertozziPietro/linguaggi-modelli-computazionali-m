
docker-compose up -d
docker ps
nc localhost 1337
docker-compose down
docker ps

S → A  
A → C | B C  
B → !
C → E | C D E  
D → & | | 
E → F | B F
F → H | F G H  
G → < | > | =  
H → J | H I J  
I → * | /  
J → L | J K L  
K → + | -  
L → b

S -> A -> BC -> !C -> ... -> !L -> !b

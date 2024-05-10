                                        LIBRARY
This application is for online library.
                                        The functions:
1) Users will can register/login (future)
2) Admin/Administration will add/update books
3) We have an publisher and Authors of books
4) Each books has own isbn
5) We can add/delete publisher and Authors
6) We have an relationships between Author-Book-Publisher
         <img width="1453" alt="Screen Shot 2024-03-25 at 17 14 14" src="https://github.com/grizzli03/library/assets/102717442/8956e66e-a49e-4db5-8b83-0c67a6a204d0">

### How to run in docker
Before starting, it is required to install docker to your machine.
First of all you have to install application in maven settings and then run
1. `docker build -t libraryapi .`
2. `docker run -p 8000:8777 libraryapi`
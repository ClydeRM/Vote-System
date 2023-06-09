# Vote-System

##  Docker Compose
要在 `vote/` 之下
```sh
docker compose up -d
```

##  Start Vue
要在 `vote/vue/vote-app/` 之下
```sh
npm install

npm run serve
```

##  Test Data
```
ACC: leo@mail.com
PWD: 123456

ACC: sandy@mail.com
PWD: 123456

ACC: randy@mail.com
PWD: 123456

ACC: rsy@mail.com
PWD: 123456
```


##  專案結構
```
.
├── vote
    ├── .idea/ intellij 設定
    ├── .mvm/
    ├── DB
         ├── DDL.sql
         └── DML.sql
    ├── src/
         └──main
             ├──java
                 └── com/bank/vote
                    ├── auth
                        ├── DTO
                            ├── AuthenticationRequest // LoginDTO email & pwd
                            ├── AuthenticationResponse // ResponseDTO at & rt
                            └── RegisterRequest// RegisterDTO uersname email & pwd 
                        ├── AuthenticationController // register authenticate refresh 路由
                        └── AuthenticationService // Authentication Business Login
                    ├── common
                        └── Exceptions
                            ├── EmailAlreadyRegisteredException
                            ├── VoteItemAlreadyExistedException
                            └── VoteItemNotFoundException
                    ├── config
                        ├── ApplicationConfig // AuthenticationProvider AuthenticationManager etc..
                        ├── JwtAuthenticationFilter // Extends OncePerRequestFilter 
                        ├── JwtService // ExtractClaim genToken validateToken
                        ├── LogoutService // Implement LogoutHandler Logout Logic
                        └── SecurityConfiguraion // Setup FilterChain
                    ├── dashboard
                        └──  DashboardController // Fetch and handle VoteItem
                    ├── token
                        ├── TokenType // Token 
                        ├── TypeToken // Token Entity
                        └── TokenRepository // Repository
                    ├── user
                        ├── Role // Role Type
                        ├── User // User Entity
                        └── UserRepository  // Repository
                    ├── voteitem
                        ├── VoteItem // VoteItem Entity
                        ├── VoteItemRepository // Repository
                        └── VoteItemService //  VoteItem Business Logic
                    ├── votemodule
                        ├── DTO
                            ├── VoteRecordRequest // VoteRecordDTO
                            └── VoteRecordResult // VoteRecordDTO 
                        └── VoteController // getAllVoteRecord & Voting
                    ├── voterecord
                        ├── VoteRecord // VoteRecord Entity
                        ├── VoteRecordService // Voting Business Logic
                        └── VoteRecordRepository // Repository 
                    └──  VoteApplication // main
             └──resurces
                 └── application.yml // 環境變數
    ├── test
    ├── vue <---------- 前端
       └── vote-app
            ├── public
                └── index.html
            ├── src
                ├── assets
                ├── components
                ├── router
                    └── index.js // Vue Router
                ├── store
                    └── index.js // Vuex store
                ├── views
                    ├── HomeView // Home
                    ├── DashboardView // Dashbord
                    ├── LoginView // Login
                    ├── RegisterView // Signup
                    └── VoteView // Polling 
                ├── App.vue 
                └── main.js // main
            ├── .gitignore
            ├── package-lock.json
            ├── package.json
            ├── README.md
            └── vite.config.js
    ├── .gitignore
    ├── docker-compose.yml // 包含 postgres localhost:5432跟 pdadmin localhost:5050
    ├── Help.md
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── README.md
    └── VoteSystem.postman_collention.json // PostMan API 測試紀錄
├── External Liberies
└── Scratches and Consoles
```

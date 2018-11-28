## WaykiChain Hot-wallet Integration Servies
### Introduction
This services project is composed of a timed task, a restufl API and other support subprojects.

### Timed Chain Scan Task Project
wicc-chain-task: it periodically scans WaykiChain block data and stores them into a centralized database for ease of processing.

### Chain API Project
wicc-chain-webapi: A restful API project that enables external parties for invocation of WaykiChain hot-wallet provoded JSON-RPC interfaces

### Backend Database
* Both Timed Chain Scan task and Chain API projects utilizes a backend database for centralized persitence and they happen to use one common database (/docs/db-schema.sql).
* MySQL is selected for centralized data storage and its schema can be found in docs folder of this project.


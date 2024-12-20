sequenceDiagram RemoteController toevoegen aan Television

    User->>Controller: PUT /television/{id}/remotecontroller linkRemoteController(Long id, IdInputDto input)
    activate User
    activate Controller
    Controller->>Service: assignRemoteControllerToTelevision(id, input)
    activate Service
    Service ->> Repository: findById (televisionId)
    activate Repository
    alt Television not found
        Repository -->> Service: null
        Service -->> Controller: RecordNotFoundException("Geen televisie gevonden met id " + televisionId)
        Controller -->> User: Response 404 Not Found
    else Television found
    Repository -->> Service: Television
        Service ->> Repository: findById(inputDto.getId())
    end
    alt Remotecontroller not found
        Repository -->> Service: null
        Service -->> Controller: RecordNotFoundException("Geen afstandsbediening gevonden met id " + inputDto.getId())
        Controller -->> User: Response 404 Not Found
    else Remotecontroller found
    Repository -->> Service: RemoteController
    Service ->> Service: update Television
    Service ->> Repository: save Television
    Repository -->> Service: updated Television
    deactivate Repository
    Service ->> Service: toTelevisionDto(updated Television) 
    Service -->> Controller: televisionDto
    deactivate Service
    Controller -->> User: Response 200 ok(televisionDto)
    deactivate Controller
    end 
    deactivate User

<img src="RemoteController%20toevoegen%20aan%20Television.png">


sequenceDiagram CIModule toevoegen aan Television

    User->>Controller: PUT /television/{id}/cimodule linkCiModule(Long id, IdInputDto input)
    activate User
    activate Controller
    Controller->>Service: assignCiModuleToTelevision(id, input)
    activate Service
    Service ->> Repository: findById (televisionId)
    activate Repository
    alt Television not found
        Repository -->> Service: null
        Service -->> Controller: RecordNotFoundException("Geen televisie gevonden met id " + televisionId)
        Controller -->> User: Response 404 Not Found
    else Television found
    Repository -->> Service: Television
        Service ->> Repository: findById(inputDto.getId())
    end
    alt CiModule not found
        Repository -->> Service: null
        Service -->> Controller: RecordNotFoundException("Geen ci module gevonden met id " + inputDto.getId())
        Controller -->> User: Response 404 Not Found
    else CiModule found
    Repository -->> Service: CiModule
    Service ->> Service: update Television
    Service ->> Repository: save Television
    Repository -->> Service: updated Television
    deactivate Repository
    Service ->> Service: toTelevisionDto(updated Television) 
    Service -->> Controller: televisionDto
    deactivate Service
    Controller -->> User: Response 200 ok(televisionDto)
    deactivate Controller
    end 
    deactivate User

<img src="CIModule%20toevoegen%20aan%20Television.png">

sequenceDiagram WallBracket toevoegen aan Television

    User->>Controller: PUT /television/{id}/wallbracket linkWallBracket(Long id, IdInputDto input)
    activate User
    activate Controller
    Controller->>Service: assignWallBracketToTelevision(id, input)
    activate Service
    Service ->> Repository: findById (televisionId)
    activate Repository
    alt Television not found
        Repository -->> Service: null
        Service -->> Controller: RecordNotFoundException("Geen televisie gevonden met id " + televisionId)
        Controller -->> User: Response 404 Not Found
    else Television found
    Repository -->> Service: Television
        Service ->> Repository: findById(inputDto.getId())
    end
    alt WallBracket not found
        Repository -->> Service: null
        Service -->> Controller: RecordNotFoundException("Geen tv beugel gevonden met id " + inputDto.getId())
        Controller -->> User: Response 404 Not Found
    else WallBracket found
    Repository -->> Service: WallBracket
    Service ->> Service: update Television
    Service ->> Repository: save Television
    Repository -->> Service: updated Television
    deactivate Repository
    Service ->> Service: toTelevisionDto(updated Television) 
    Service -->> Controller: televisionDto
    deactivate Service
    Controller -->> User: Response 200 ok(televisionDto)
    deactivate Controller
    end 
    deactivate User

<img src="WallBracket%20toevoegen%20aan%20Television.png">



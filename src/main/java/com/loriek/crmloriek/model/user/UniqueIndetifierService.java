package com.loriek.crmloriek.model.user;

import com.loriek.crmloriek.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

@Service
public class UniqueIndetifierService {
    private final UserRepository userRepository;
    private final UniqueIdenfierRepository uniqueIdenfierRepository;
    public UniqueIndetifierService(UserRepository userRepository, UniqueIdenfierRepository uniqueIdenfierRepository) {
        this.userRepository = userRepository;
        this.uniqueIdenfierRepository = uniqueIdenfierRepository;
    }

    public boolean verify(String uuid) {
        return uniqueIdenfierRepository.findById(uuid).isPresent();
    }

    public User getUser(String uuid){
        try{
            User user =uniqueIdenfierRepository.findById(uuid).get().getUser();
            return user;
        }catch(NullPointerException e){
            return null;
        }
    }

    public String create(User user){
        String uuid = UUIDGenerator.generateUUID();
        while(uniqueIdenfierRepository.existsById(uuid)){
            uuid = UUIDGenerator.generateUUID(32);
        }
        uniqueIdenfierRepository.save(new UniqueIdentificator(uuid, user));
        return uuid;
    }
}

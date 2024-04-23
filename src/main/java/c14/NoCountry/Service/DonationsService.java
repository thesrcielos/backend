package c14.NoCountry.Service;

import c14.NoCountry.Entity.Donations;
import c14.NoCountry.Entity.Post;
import c14.NoCountry.Entity.Users;
import c14.NoCountry.Repository.DonationsRepository;
import c14.NoCountry.dto.donations.DonationResponse;
import c14.NoCountry.dto.donations.DonationSavingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationsService {

    private final DonationsRepository donationsRepository;
    private final DonationsMapper donationsMapper;
    private final UserService userService;
    private final PostService postService;

    public DonationResponse save(DonationSavingRequest donationSavingRequest, Integer id){
        System.out.println("el id que llega al service es : " + id);
        Donations donations = donationsMapper.donationsRequestToDonation(donationSavingRequest);
        Users user = userService.getUserFromSecurityContextHolder();
        donations.setUsers(user);
        Post post = postService.getPostById(id);
        donations.setPost(post);

        return donationsMapper.toDonationResponse(donationsRepository.save(donations));
    }

    public List<Donations> findByAll(){
        return donationsRepository.findAll();

    }
}

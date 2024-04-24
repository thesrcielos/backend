package c14.NoCountry.Service;

import c14.NoCountry.Entity.Donations;
import c14.NoCountry.dto.donations.DonationResponse;
import c14.NoCountry.dto.donations.DonationSavingRequest;
import org.springframework.stereotype.Service;

@Service
public class DonationsMapper {
    public DonationResponse toDonationResponse(Donations donations){
        if(donations == null){
            throw new NullPointerException("Donation cant be null");
        }
            return DonationResponse.builder()
                    .id(donations.getId())
                    .user_id(donations.getUser().getId())
                    .post_id(donations.getPost().getId())
                    .amount(donations.getAmount())
                    .build();
    }

    public Donations donationsRequestToDonation(DonationSavingRequest donation){
        if (donation == null){
            throw new NullPointerException("Post cant be null");
        }
        return Donations.builder()
                .amount(donation.getAmount())
                .build();
    }

}

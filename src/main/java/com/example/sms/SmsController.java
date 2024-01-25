package com.example.sms;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class SmsController {

    private final SmsUtil smsUtil;
    @GetMapping("/partner/allow/{id}")
    public String allow(@PathVariable("id") Long id, Model model) {
        Offer offer = this.offerService.getOffer(id);
        model.addAttribute("offer", offer);

        String from = offer.getPartnerPost().getAuthor().getPhoneNum();
        String to = offer.getAuthor().getPhoneNum();
        smsUtil.sendOne(from, to);
        return String.format("redirect:/partner/detail/%d", offer.getPartnerPost().getId());
}

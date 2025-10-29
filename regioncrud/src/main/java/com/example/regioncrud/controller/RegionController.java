package com.example.regioncrud.controller;

import com.example.regioncrud.model.Region;
import com.example.regioncrud.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
@RequestMapping("/regions") 
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping
    public String getAllRegions(Model model) {
        model.addAttribute("regions", regionRepository.findAll());
        return "regions"; 
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("region", new Region());
        return "add-region"; 
    }

    @PostMapping("/save")
    public String saveRegion(@ModelAttribute("region") Region region) {
        regionRepository.save(region);
        return "redirect:/regions";
    }

    @GetMapping("/{id}")
    public String showRegionDetail(@PathVariable Long id, Model model) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid region Id:" + id));
        model.addAttribute("region", region);
        return "detail-region";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid region Id:" + id));
        model.addAttribute("region", region);
        return "update-region";
    }

    @PostMapping("/update/{id}")
    public String updateRegion(@PathVariable Long id, @ModelAttribute("region") Region regionDetails) {
        regionDetails.setId(id);
        regionRepository.save(regionDetails);
        return "redirect:/regions";
    }

    @GetMapping("/delete/{id}")
    public String deleteRegion(@PathVariable Long id) {

        regionRepository.deleteById(id);

        return "redirect:/regions";
    }
}
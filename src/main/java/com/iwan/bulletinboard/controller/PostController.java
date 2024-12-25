package com.iwan.bulletinboard.controller;

import com.iwan.bulletinboard.model.Post;
import com.iwan.bulletinboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/board")
public class PostController {
    @Autowired
    private PostService postService;

    public class DateFormatter {
        public static String formatDate(LocalDateTime dateTime) {
            if (dateTime == null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mm:ss a");
            return dateTime.format(formatter);
        }
    }

    //ini untuk nampilin data di menu utama
    @GetMapping
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();

        for (Post post : posts) {
                String formattedCreated_at = DateFormatter.formatDate(post.getCreated_at());
                post.setFormattedCreated_at(formattedCreated_at);
                System.out.println("Formatted Creation Date: " + formattedCreated_at); // Debug
        }

        model.addAttribute("posts", posts);
        return "posts/list";

    }

    //ini untuk  ke form tombol buat postingan baru
    @GetMapping("/create-new-post")
    public String createNewPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }
    //ini untuk tombol simpan
    @PostMapping("/submit-new-post")
    public String SubmitToCreatePost(@ModelAttribute Post post, BindingResult result, RedirectAttributes redirectAttributes) {
        boolean isKorean = Pattern.matches("[\\p{IsHangul}\\s]+", post.getTitle());
        int maxTitleLength = isKorean ? 50 : 100;

        if (post.getTitle().length() > maxTitleLength) {
            result.rejectValue("title", "error.post", "Title is too long. Maximum " + maxTitleLength + " characters.");
        }

        isKorean = Pattern.matches("[\\p{IsHangul}\\s]+", post.getAuthor());
        int maxAuthorLength = 10;

        if (post.getAuthor().length() > maxAuthorLength) {
            result.rejectValue("author", "error.post", "Author name is too long. Maximum " + maxAuthorLength + " characters.");
        }

        if (result.hasErrors()) {
            return "posts/create";
        }
        postService.createPost(post);
        redirectAttributes.addFlashAttribute("success", "Post successfully created!");
        return "redirect:/board";
    }


    //nampilin postingan
    @GetMapping("detail-post/{id}")
    public String viewAnPost(@PathVariable Long id, @RequestParam int postNumber, Model model) {
        Post post = postService.getPostByIdView(id);

        String formattedUpdated_at = null;
        String formattedCreated_at = null;
        if (post.getCreated_at() != null) {
            formattedCreated_at = DateFormatter.formatDate(post.getCreated_at());
        }
        if (post.getUpdated_at() != null) {
            formattedUpdated_at = DateFormatter.formatDate(post.getUpdated_at());
        }

        post.setFormattedUpdated_at(formattedUpdated_at);
        post.setFormattedCreated_at(formattedCreated_at);

        System.out.println("tanggal apdet dari DB: " + post.getUpdated_at());
        System.out.println("tgl buat dari DB: " + post.getCreated_at());
        System.out.println( "tgl buat versi mmddyy : " + formattedCreated_at);
        System.out.println( "tgl edit versi mmddyy : " + formattedCreated_at);


        model.addAttribute("post", post);
        model.addAttribute("postNumber", postNumber);
        return "posts/view";
    }


    //ke Halaman edit
    @GetMapping("/edit/{id}")
    public String editPos(@PathVariable Long id, @RequestParam int postNumber, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("postNumber", postNumber);
        return "posts/edit";
    }


    //Subbbmit Edit
    @PostMapping("/submit-edit/{id}")
    public String submitEditPost(
            @PathVariable Long id,
            @RequestParam int postNumber,
            @ModelAttribute Post post,
            BindingResult result, // Harus setelah @ModelAttribute
            RedirectAttributes redirectAttributes,
            Model model) {
        // Validasi manual
        boolean isKorean = Pattern.matches("[\\p{IsHangul}\\s]+", post.getTitle());
        int maxTitleLength = isKorean ? 50 : 100;

        if (post.getTitle().length() > maxTitleLength) {
            result.rejectValue("title", "error.post", "Title is too long. Maximum " + maxTitleLength + " characters.");
        }

        // Jika ada error, kembali ke halaman edit
        if (result.hasErrors()) {
            model.addAttribute("post", post); // Pastikan objek post disediakan
            model.addAttribute("postNumber", postNumber);
            return "posts/edit";
        }

        // Simpan perubahan jika validasi sukses
        try {
            postService.updatePost(post);
            redirectAttributes.addFlashAttribute("success", "Post has been successfully modifying!");
            return "redirect:/board/detail-post/" + id + "?postNumber=" + postNumber;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("post", post); // Pastikan post disediakan
            model.addAttribute("postNumber", postNumber);
            return "posts/edit";
        }
    }





    //ngapus postingan
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, @RequestParam String password, @RequestParam(required = false) Integer postNumber, RedirectAttributes redirectAttributes, Model model) {
        try {
            postService.deletePost(id, password);
            redirectAttributes.addFlashAttribute("success", "Post has been successfully deleted!");
            return "redirect:/board"; // Redirect ke halaman list jika berhasil
        } catch (IllegalArgumentException e) {
            Post post = postService.getPostById(id);
            String formattedUpdated_at = null;
            String formattedCreated_at = null;
            if (post.getCreated_at() != null) {
                formattedCreated_at = DateFormatter.formatDate(post.getCreated_at());
            }
            if (post.getUpdated_at() != null) {
                formattedUpdated_at = DateFormatter.formatDate(post.getUpdated_at());
            }

            post.setFormattedUpdated_at(formattedUpdated_at);
            post.setFormattedCreated_at(formattedCreated_at);
            model.addAttribute("post", post);
            model.addAttribute("postNumber", postNumber);
            model.addAttribute("error", e.getMessage());
            return "posts/view";
        }
    }


}

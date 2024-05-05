package artemtsmyg.ru.DZ_sem7.controller;

import artemtsmyg.ru.DZ_sem7.service.AdminService;
import artemtsmyg.ru.DZ_sem7.service.ErrorService;
import artemtsmyg.ru.DZ_sem7.service.HomeService;
import artemtsmyg.ru.DZ_sem7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер.
 * Содержит поля сервисов:
 *  - сервис работы с домашней страницей;
 *  - сервис пользователей;
 *  - сервис админитсраторов;
 *  -сервис ошибок
 */
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;
    private final UserService userService;
    private final AdminService adminService;
    private final ErrorService errorService;

    /**
     * Метод домашней страницы
     * @param model - заготовка страницы index.html
     * @return - готовая страница index.html
     * Загружает в модель страницы текст и изображение
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("text", homeService.getText());
        model.addAttribute("home_cat", homeService.getCat());
        return "index";
    }

    /**
     * Метод переадресовки на домашнюю страницу
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "redirect:/";
    }

    /**
     * Метод подготовки страницы пользователей
     * @param model - заготовка страницы user.html
     * @return - страница ser.html
     * Загружает в модель страницы необходимый текст.
     */
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("text", userService.getText());
        return "user";
    }

    /**
     * Метод подготовки страницы администратора
     * @param model - заготовка страницы admin.html
     * @return - страница admin.html
     * Загружает в модель страницы необходимый текст.
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("text", adminService.getText());
        return "admin";
    }


    /**
     * Метод подготовки страницы приватных данных
     * @param model - заготовка страницы private-data.html
     * @return - страница private-data.html
     * Загружает в модель страницы текст и изображение
     */
    @GetMapping("/private-data")
    public String private_data(Model model) {
        model.addAttribute("text", adminService.getPrivateText());
        model.addAttribute("private_cat", adminService.getCat());
        return "private-data";
    }

    /**
     * Метод подготовки страницы публичных данных
     * @param model - заготовка страницы public-data.html
     * @return - страница public-data.html
     * Загружает в модель страницы текст и изображение
     */
    @GetMapping("/public-data")
    public String public_data(Model model) {
        model.addAttribute("text", userService.getPublicText());
        model.addAttribute("public_cat", userService.getCat());
        return "public-data";
    }

    /**
     * Метод подготовки страницы предупреждения о запрете доступа к запрошенным данным
     * @param model - заготовка страницы index.html
     * @return - страница index.html
     * Загружает в модель страницы текст о запрете доступа и соответствующее изображение
     */
    @GetMapping("/error-page")
    public String accessDeniedPage(Model model) {
        model.addAttribute("text", errorService.getText());
        model.addAttribute("home_cat", errorService.getCat());
        return "index";

    }

}
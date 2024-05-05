package artemtsmyg.ru.DZ_sem7.service;

import org.springframework.stereotype.Service;

/**
 * Сервис домашней страницы
 */
@Service
public class HomeService {

    /**
     * Заголовок домашней страницы
     * @return текст заголовка
     */
    public String getText() {
        return "Домашняя страница";
    }

    /**
     * Изображение на домашней странице
     * @return картинка
     */
    public String getCat() {
        return "/img/pet-house.svg";
    }
}
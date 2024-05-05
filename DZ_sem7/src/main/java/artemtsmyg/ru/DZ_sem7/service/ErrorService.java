package artemtsmyg.ru.DZ_sem7.service;

import org.springframework.stereotype.Service;

/**
 * Сервис отказа в доступе
 */
@Service
public class ErrorService {

    /**
     * Заголовок страницы отказа в доступе
     * @return текст заголовка страницы
     */
    public String getText() {
        return "Доступ к запрошенной странице запрещен.";
    }

    /**
     * Изображение на странице отказа в доступе
     * @return картинка
     */
    public String getCat() {
        return "/img/error-cat.svg";
    }
}

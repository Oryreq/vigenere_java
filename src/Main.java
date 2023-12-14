import app.VigenereService;

/**
 * Программа по дисциплине "Информационная безопасность".
 * Реализует шифр "Квадрат Виженера".
 * Программа поддерживает любые языки, если задать алфавит, но будет работать на примере русского.
 * Может работать с большинством лишних спец-символов в тексте для шифра.
 * Ключ обязателен как слово без лишних спец-символов.
 *
 * @author Ашихмин Всеволод.
 * Группа: ПИ-41(э).
 * Дата: 13.12.2023.
 */
public class Main {

    private static final String[] ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toUpperCase().split("");

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        VigenereService service = new VigenereService(ALPHABET);
        String message = "У людей уже не хватает времени что-либо узнавать." +
                "Они покупают вещи готовыми в магазинах." +
                "Но ведь нет таких магазинов, где торговали бы друзьями, и потому люди больше не имеют друзей";
        String key = "арбуз";

        System.out.println("Исходный текст: " + message + " | ключ: " + key);
        System.out.println("-----------------------------------------------------------------------------------------");
        var encryptedResult = service.encrypt(message, key);
        System.out.println("Зашифрованный текст: " + encryptedResult.encryptedMessage());

        System.out.println("-----------------------------------------------------------------------------------------");

        var decryptedResult = service.decrypt(encryptedResult.encryptedMessage(), key);
        System.out.println("Расшифрованный текст: " + decryptedResult);

        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void test2() {
        VigenereService service = new VigenereService(ALPHABET);
        String message = "Люди забираются в скорые поезда, но они уже сами не понимают, чего ищут." +
                "Поэтому они не знают покоя и бросаются то в одну сторону, то в другую..." +
                "И все напрасно.";
        String key = "пальма";

        System.out.println("Исходный текст: " + message + " | ключ: " + key);
        System.out.println("-----------------------------------------------------------------------------------------");
        var encryptedResult = service.encrypt(message, key);
        System.out.println("Зашифрованный текст: " + encryptedResult.encryptedMessage());

        System.out.println("-----------------------------------------------------------------------------------------");

        var decryptedResult = service.decrypt(encryptedResult.encryptedMessage(), key);
        System.out.println("Расшифрованный текст: " + decryptedResult);

        System.out.println("-----------------------------------------------------------------------------------------");
    }
}

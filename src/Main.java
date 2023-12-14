import app.VigenereService;

/**
 * ��������� �� ���������� "�������������� ������������".
 * ��������� ���� "������� ��������".
 * ��������� ������������ ����� �����, ���� ������ �������, �� ����� �������� �� ������� ��������.
 * ����� �������� � ������������ ������ ����-�������� � ������ ��� �����.
 * ���� ���������� ��� ����� ��� ������ ����-��������.
 *
 * @author ������� ��������.
 * ������: ��-41(�).
 * ����: 13.12.2023.
 */
public class Main {

    private static final String[] ALPHABET = "��������������������������������".toUpperCase().split("");

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        VigenereService service = new VigenereService(ALPHABET);
        String message = "� ����� ��� �� ������� ������� ���-���� ��������." +
                "��� �������� ���� �������� � ���������." +
                "�� ���� ��� ����� ���������, ��� ��������� �� ��������, � ������ ���� ������ �� ����� ������";
        String key = "�����";

        System.out.println("�������� �����: " + message + " | ����: " + key);
        System.out.println("-----------------------------------------------------------------------------------------");
        var encryptedResult = service.encrypt(message, key);
        System.out.println("������������� �����: " + encryptedResult.encryptedMessage());

        System.out.println("-----------------------------------------------------------------------------------------");

        var decryptedResult = service.decrypt(encryptedResult.encryptedMessage(), key);
        System.out.println("�������������� �����: " + decryptedResult);

        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void test2() {
        VigenereService service = new VigenereService(ALPHABET);
        String message = "���� ���������� � ������ ������, �� ��� ��� ���� �� ��������, ���� ����." +
                "������� ��� �� ����� ����� � ��������� �� � ���� �������, �� � ������..." +
                "� ��� ��������.";
        String key = "������";

        System.out.println("�������� �����: " + message + " | ����: " + key);
        System.out.println("-----------------------------------------------------------------------------------------");
        var encryptedResult = service.encrypt(message, key);
        System.out.println("������������� �����: " + encryptedResult.encryptedMessage());

        System.out.println("-----------------------------------------------------------------------------------------");

        var decryptedResult = service.decrypt(encryptedResult.encryptedMessage(), key);
        System.out.println("�������������� �����: " + decryptedResult);

        System.out.println("-----------------------------------------------------------------------------------------");
    }
}

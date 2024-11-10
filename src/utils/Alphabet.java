package utils;

public enum Alphabet {
    VIETNAME_ALPHABET(new char[]{

            // Chữ hoa
            'A', 'Ă', 'Â', 'B', 'C', 'D', 'Đ', 'E', 'Ê', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'Ô', 'Ơ',
            'P', 'Q', 'R', 'S', 'T', 'U', 'Ư', 'V', 'X', 'Y',

            // Chữ thường
            'a', 'ă', 'â', 'b', 'c', 'd', 'đ', 'e', 'ê', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'ô', 'ơ',
            'p', 'q', 'r', 's', 't', 'u', 'ư', 'v', 'x', 'y',

            // Nguyên âm với dấu (Chữ hoa)
            'Á', 'À', 'Ả', 'Ã', 'Ạ', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ', 'Ấ', 'Ầ', 'Ẩ', 'Ẫ', 'Ậ',
            'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ',
            'Í', 'Ì', 'Ỉ', 'Ĩ', 'Ị',
            'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ', 'Ố', 'Ồ', 'Ổ', 'Ỗ', 'Ộ', 'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ',
            'Ú', 'Ù', 'Ủ', 'Ũ', 'Ụ', 'Ứ', 'Ừ', 'Ử', 'Ữ', 'Ự',
            'Ý', 'Ỳ', 'Ỷ', 'Ỹ', 'Ỵ',

            // Nguyên âm với dấu (Chữ thường)
            'á', 'à', 'ả', 'ã', 'ạ', 'ắ', 'ằ', 'ẳ', 'ẵ', 'ặ', 'ấ', 'ầ', 'ẩ', 'ẫ', 'ậ',
            'é', 'è', 'ẻ', 'ẽ', 'ẹ', 'ế', 'ề', 'ể', 'ễ', 'ệ',
            'í', 'ì', 'ỉ', 'ĩ', 'ị',
            'ó', 'ò', 'ỏ', 'õ', 'ọ', 'ố', 'ồ', 'ổ', 'ỗ', 'ộ', 'ớ', 'ờ', 'ở', 'ỡ', 'ợ',
            'ú', 'ù', 'ủ', 'ũ', 'ụ', 'ứ', 'ừ', 'ử', 'ữ', 'ự',
            'ý', 'ỳ', 'ỷ', 'ỹ', 'ỵ',




            // số
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',

    }),
    ENGLISH_ALPHABET(new char[]{
            // Chữ hoa
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

            // Chữ thường
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',

            // số
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',

    })
    ;

    private final char[] alphabet;
    Alphabet(char[] alphabet) {
        this.alphabet = alphabet;
    }
    public char[] getAlphabet() {return alphabet;}

    public static void main(String[] args) {
        int vietnameseAlphabetMod = Alphabet.VIETNAME_ALPHABET.getAlphabet().length;
        System.out.println("Mod của bảng chữ cái tiếng Việt: " + vietnameseAlphabetMod);
    }
}

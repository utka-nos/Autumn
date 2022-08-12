package org.example.autumn;

public class ToDelete {
    public static void main(String[] args) {
        /*ContextConfig ctxConf = new ContextConfigImpl("org.example.autumn");
        AppContext ctx = new AppContextImpl(ctxConf);
        System.out.println("quality: " + ctx.getObject(Car.class).quality());

        System.out.println("end");*/
        System.out.println(Solution.myAtoi("         +10523538441s"));
    }

    public static class Solution {
        public static int myAtoi(String s) {

            StringBuilder sb = new StringBuilder();
            int i = 0;
            int sign = 1;
            while(i < s.length() && (s.charAt(i) == ' ')) {
                i++;
            }

            if(s.length() > i && s.charAt(i) == '-') {
                sb.append(s.charAt(i));
                i++;
            }
            else if(i < s.length() && s.charAt(i) == '+') {
                sb.append(s.charAt(i));
                i++;
            }

            while(i < s.length() && s.charAt(i) - '0' == 0) i++;

            //if(s.length() > i + 1 && s.charAt(i + 1) - '0' < 0 || s.charAt(i + 1) > 9) return 0;

            while(i < s.length() && (s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9)) {

                sb.append(s.charAt(i));
                i++;
            }
            String num = sb.toString();
            if(num.length() > 11) return num.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            if(num.length() > 10) {
                if(num.charAt(0) != '-' && num.charAt(0) != '+') {
                    return num.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            }
            int ans = 0;
            i = 0;
            if(num.length() == 0) return 0;
            if(num.charAt(i) == '-') {
                i++;
                sign = -1;
            }
            else if(num.charAt(i) == '+') {
                i++;
                sign = 1;
            }
            int pow = num.length() - i;
            if(i < num.length() && num.charAt(i) - '0' > 2 && num.length() - i >= 10) return num.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for(int j = i; j < num.length(); j++) {
                int app = pow10(num.length() - j - 1) * (num.charAt(j) - '0');
                if(Integer.MAX_VALUE - app < ans) return num.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                ans += app;
            }
            return ans * sign;
        }

        static int pow10(int p) {
            int num = 1;
            for(int i = 0; i < p; i++) {
                num = num * 10;
            }
            return num;
        }
    }
}

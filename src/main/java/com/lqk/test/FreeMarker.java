package com.lqk.test;

public class FreeMarker {
    public static String javaScriptStringEnc(String s)
    {
        int ln = s.length();
        for (int i = 0; i < ln; i++) {
            char c = s.charAt(i);
            if ((c == '"') || (c == '\'') || (c == '\\') || (c == '>') || (c < ' ')) {
                StringBuffer b = new StringBuffer(ln + 4);
                b.append(s.substring(0, i));
                while (true) {
                    if (c == '"') {
                        b.append("\\\"");
                    } else if (c == '\'') {
                        b.append("\\'");
                    } else if (c == '\\') {
                        b.append("\\\\");
                    } else if (c == '>') {
                        b.append("\\>");
                    } else if (c < ' ') {
                        if (c == '\n') {
                            b.append("\\n");
                        } else if (c == '\r') {
                            b.append("\\r");
                        } else if (c == '\f') {
                            b.append("\\f");
                        } else if (c == '\b') {
                            b.append("\\b");
                        } else if (c == '\t') {
                            b.append("\\t");
                        } else {
                            b.append("\\x");
                            int x = c / '\020';
                            b.append((char)(x < 10 ? x + 48 : x - 10 + 65));

                            x = c & 0xF;
                            b.append((char)(x < 10 ? x + 48 : x - 10 + 65));
                        }
                    }
                    else {
                        b.append(c);
                    }
                    i++;
                    if (i >= ln) {
                        return b.toString();
                    }
                    c = s.charAt(i);
                }
            }
        }
        return s;
    }
}

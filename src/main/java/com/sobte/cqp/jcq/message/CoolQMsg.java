package com.sobte.cqp.jcq.message;

import com.sobte.cqp.jcq.util.StringHelper;

/**
 * Created by Sobte on 2018/4/24.<br>
 * Time: 2018/4/24 19:07<br>
 * Email: i@sobte.me<br>
 * 酷Q消息解析类，普通消息和酷Q码的集合
 *
 * @author Sobte
 */
public class CoolQMsg extends BaseMsg<ActionMsg> {

    /**
     * 无参初始化
     */
    public CoolQMsg() {
    }

    /**
     * 初始化
     *
     * @param code 要解析的消息
     */
    public CoolQMsg(String code) {
        analysis(code);
    }

    /**
     * 解析CQ码
     *
     * @param code 要解析的 酷Q码
     */
    public void analysis(String code) {
        if (StringHelper.isTrimEmpty(code))
            return;
        int length = code.length();
        int begin = 0;
        for (int i = 0; i < length; i++) {
            switch (code.charAt(i)) {
                case '[':
                    int idx = code.indexOf(']', i);
                    if (idx == -1) {
                        i = length;// 设置为超出，则退出循环
                        add(new ActionMsg(code.substring(begin, i)));
                        continue;
                    }
                    if (code.charAt(++i) == 'C' && code.charAt(++i) == 'Q' && code.charAt(++i) == ':') {
                        int endidx = code.indexOf(',', ++i);
                        if (endidx == -1 || endidx > idx)
                            endidx = idx;
                        add(ActionCode.analysis(code.substring(i, endidx), code.substring(endidx, idx)));
                        begin = i = idx;
                        begin++;
                        break;
                    }
                default:
                    idx = code.indexOf('[', i);
                    if (idx == -1)
                        idx = length;
                    add(new ActionMsg(code.substring(begin, idx)));
                    begin = i = idx;
                    i--;
                    break;
            }
        }
    }

}

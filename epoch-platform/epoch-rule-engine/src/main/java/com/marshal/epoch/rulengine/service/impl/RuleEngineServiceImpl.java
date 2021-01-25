package org.epoch.rulengine.service.impl;

import java.util.List;

import org.epoch.rulengine.service.RuleEngineService;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>name:RuleEngineServiceImpl</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/9
 */
public class RuleEngineServiceImpl implements RuleEngineService {
    private static final String RULE = "package book.discount\n" +
            "import org.epoch.rulengine.test.dto.Order\n" +
            "\n" +
            "//规则一：所购图书总价在100元以下的没有优惠\n" +
            "rule \"book_discount_1\"\n" +
            "    when\n" +
            "        $order:Order(originalPrice < 100)\n" +
            "    then\n" +
            "        $order.setRealPrice($order.getOriginalPrice());\n" +
            "        System.out.println(\"成功匹配到规则一：所购图书总价在100元以下的没有优惠\");\n" +
            "end\n" +
            "\n" +
            "//规则二：所购图书总价在100到200元的优惠20元\n" +
            "rule \"book_discount_2\"\n" +
            "    when\n" +
            "        $order:Order(originalPrice < 200 && originalPrice >= 100)\n" +
            "    then\n" +
            "        $order.setRealPrice($order.getOriginalPrice() - 20);\n" +
            "        System.out.println(\"成功匹配到规则二：所购图书总价在100到200元的优惠20元\");\n" +
            "end\n" +
            "\n" +
            "//规则三：所购图书总价在200到300元的优惠50元\n" +
            "rule \"book_discount_3\"\n" +
            "    when\n" +
            "        $order:Order(originalPrice <= 300 && originalPrice >= 200)\n" +
            "    then\n" +
            "        $order.setRealPrice($order.getOriginalPrice() - 50);\n" +
            "        System.out.println(\"成功匹配到规则三：所购图书总价在200到300元的优惠50元\");\n" +
            "end\n" +
            "\n" +
            "//规则四：所购图书总价在300元以上的优惠100元\n" +
            "rule \"book_discount_4\"\n" +
            "    when\n" +
            "        $order:Order(originalPrice >= 300)\n" +
            "    then\n" +
            "        $order.setRealPrice($order.getOriginalPrice() - 100);\n" +
            "        System.out.println(\"成功匹配到规则四：所购图书总价在300元以上的优惠100元\");\n" +
            "end\n";

    @Autowired
    private KieBase kieBase;

    @Override
    public void match(List<Object> data) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(RULE, ResourceType.DRL);
        KieSession kieSession = kieHelper.build().newKieSession();
        for (Object fact : data) {
            kieSession.insert(fact);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Override
    public void match(String ruleEngineType, List<Object> data) {
        KieSession kieSession = kieBase.newKieSession();
        for (Object fact : data) {
            kieSession.insert(fact);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}

//package org.epoch.iam.config;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Proxy;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import com.alibaba.druid.sql.SQLUtils;
//import com.alibaba.druid.sql.ast.SQLStatement;
//import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
//import com.alibaba.druid.sql.ast.statement.*;
//import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
//import com.alibaba.druid.sql.parser.SQLExprParser;
//import com.alibaba.druid.sql.parser.SQLStatementParser;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.apache.ibatis.executor.statement.RoutingStatementHandler;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.plugin.*;
//import org.epoch.core.util.ReflectionUtils;
//import org.springframework.stereotype.Component;
//
///**
// * @author Marshal
// * @date 2021/3/22
// */
//@Slf4j
//@Component
//@Intercepts({
//        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
//public class DataFilterInterceptor implements Interceptor {
//    private static final String TB_ROLE = "sys_role";
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        RoutingStatementHandler handler = getRoutingStatementHandler(invocation);
//        StatementHandler delegate = (StatementHandler) ReflectionUtils.getFieldValue(handler, "delegate");
//        BoundSql boundSql = delegate.getBoundSql();
//        String sql = boundSql.getSql();
//        String newSql = filter(sql);
//        ReflectionUtils.setFieldValue(boundSql, "sql", newSql);
//        return invocation.proceed();
//    }
//
//    private RoutingStatementHandler getRoutingStatementHandler(Object invocation) {
//        Object target = invocation;
//        if (target instanceof Invocation) {
//            return getRoutingStatementHandler(((Invocation) target).getTarget());
//        } else if (target instanceof RoutingStatementHandler) {
//            return (RoutingStatementHandler) target;
//        } else if (target instanceof Proxy) {
//            InvocationHandler invocationHandler = Proxy.getInvocationHandler(target);
//            Object targetObj = ReflectionUtils.getFieldValue(invocationHandler, "target");
//            return getRoutingStatementHandler(targetObj);
//        }
//        return null;
//    }
//
//    private String filter(String sql) {
//        SQLStatementParser parser = new MySqlStatementParser(sql);
//        List<SQLStatement> statementList = parser.parseStatementList();
//        if (statementList == null || statementList.size() < 1) {
//            return sql;
//        }
//
//        SQLStatement sqlStatement = statementList.get(0);
//        if (!(sqlStatement instanceof SQLSelectStatement)) {
//            return sql;
//        }
//        SQLSelect select = ((SQLSelectStatement) sqlStatement).getSelect();
//        dealQuery(select);
//        return SQLUtils.toSQLString(statementList, "mysql");
//
//    }
//
//    private void dealQuery(SQLSelect select) {
//        if (select == null) {
//            return;
//        }
//        SQLSelectQuery queryPart = select.getQuery();
//        dealQueryBlock(queryPart);
//    }
//
//    private void dealQueryBlock(SQLSelectQuery selectQuery) {
//        if (selectQuery == null) {
//            return;
//        }
//        if (selectQuery instanceof SQLSelectQueryBlock) {
//            SQLSelectQueryBlock query = (SQLSelectQueryBlock) selectQuery;
//            SQLTableSource from = query.getFrom();
//
//            // select part
//            List<SQLSelectItem> selectList = query.getSelectList();
//            selectList.forEach(selectItem -> {
//                if (selectItem.getExpr() instanceof SQLQueryExpr) {
//                    SQLQueryExpr expr = (SQLQueryExpr) selectItem.getExpr();
//                    SQLSelect subQuery = expr.getSubQuery();
//                    dealQuery(subQuery);
//                }
//            });
//
//            // from part
//            dealSQLTableSource(from, query);
//
//            //TODO where part
//            // SQLExpr where = query.getWhere();
//        } else if (selectQuery instanceof SQLUnionQuery) {
//            SQLSelectQuery left = ((SQLUnionQuery) selectQuery).getLeft();
//            SQLSelectQuery right = ((SQLUnionQuery) selectQuery).getRight();
//            dealQueryBlock(left);
//            dealQueryBlock(right);
//        }
//    }
//
//    private void dealSQLTableSource(SQLTableSource tableSource, SQLSelectQueryBlock query) {
//        if (tableSource == null) {
//            return;
//        }
//        if (tableSource instanceof SQLJoinTableSource) {
//            SQLJoinTableSource joinFrom = (SQLJoinTableSource) tableSource;
//            SQLTableSource left = joinFrom.getLeft();
//            SQLTableSource right = joinFrom.getRight();
//            dealSQLTableSource(left, query);
//            dealSQLTableSource(right, query);
//        } else if (tableSource instanceof SQLSubqueryTableSource) {
//            SQLSubqueryTableSource subqueryTableSource = (SQLSubqueryTableSource) tableSource;
//            SQLSelect select = subqueryTableSource.getSelect();
//            dealQuery(select);
//        } else {
//            SQLTableSource role = tableSource.findTableSource("sys_role");
//            SQLTableSource user = tableSource.findTableSource("sys_user");
//
//            if (user != null) {
//                // sql的最外层查询包含的con_contract表
//                String alias = user.getAlias();
//                getUserSQLPart(alias, query);
//            }
//
//            if (role != null) {
//                // sql的最外层查询包含的prj_project表
//                String alias = role.getAlias();
//                getRoleSQLPart(alias, query);
//            }
//        }
//    }
//
//    private void getUserSQLPart(String alias, SQLSelectQueryBlock query) {
//        if (StringUtils.isEmpty(alias)) {
//            alias = "sys_user";
//        }
//        getSQLPart(alias, query);
//    }
//
//    private void getRoleSQLPart(String alias, SQLSelectQueryBlock query) {
//        if (StringUtils.isEmpty(alias)) {
//            alias = "sys_role";
//        }
//        getSQLPart(alias, query);
//    }
//
//    private void getSQLPart(String alias, SQLSelectQueryBlock query) {
//        dealWhere(query, alias);
//
//    }
//
//    private void dealWhere(SQLSelectQueryBlock query, String alias) {
//
//        List<String> ids = new ArrayList<>();
//        ids.add("10001");
//        ids.add("10002");
//
//        String columnPrefix = " " + alias + ".id in ";
//        String columnSuffix = "' ";
//        ArrayList<String> params = new ArrayList<>();
//        params.add(columnPrefix + "('10001','10002')");
////        for (int i = 0; i < rules.size(); i++) {
////            params.add(columnPrefix + rules.get(i) + columnSuffix);
////        }
//
////        String id_field = null;
////        switch (documentCatagory) {
////            case DOC_BP_PREFIX:
////                id_field = alias + ".bp_id";
////                break;
////            case DOC_CON_PREFIX:
////                id_field = alias + ".contract_id";
////                break;
////            case DOC_PRJ_PREFIX:
////                id_field = alias + ".project_id";
////                break;
////            default:
////                break;
////        }
////        if (id_field != null) {
////            String whiteListSql = "  " + id_field + " in " +
////                    "(select document_id " +
////                    " from   SYS_USER_AUTHORITY_TRX " +
////                    " where " +
////                    "        document_category =  '" + documentCatagory + "' " +
////                    "    and document_id =  " + id_field +
////                    "    and user_id = " + userId + ") ";
////            log.debug("add white list sql: ({})", whiteListSql);
////            params.add(whiteListSql);
////        }
//        String or = StringUtils.join(params, "or");
//        SQLExprParser sqlExprParser = new SQLExprParser("(" + or + ")");
//        query.addCondition(sqlExprParser.expr());
//    }
//
//    @Override
//    public Object plugin(Object o) {
//        return Plugin.wrap(o, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}

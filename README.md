# Lost-Found-backend
校园寻物系统后端，IDEA项目，基于Java开发，没有使用框架，也没有后台管理页面，可人肉运维（手动登录sql，查数据库维护）。

# todo list
- [x] 挖坑
- [ ] 改用SpringBoot重构
- [ ] 增加后台管理页面

# 部署方法
克隆项目到本地，修改数据库连接地址、用户名及密码（src/DAO/DatabaseConnection.java）**数据库文件位于src/sql文件夹下** ，直接导入到数据库即可，需配合[校园寻物系统前端](https://github.com/weingxing/Lost-Found-front)使用。

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DataBaseDao {

	private static String drive;
	private static String url;
	private static String userName;
	private static String password;

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public static void setDrive(String drive) {
		DataBaseDao.drive = drive;
	}

	public static void setUserName(String userName) {
		DataBaseDao.userName = userName;
	}

	public static void setPassword(String password) {
		DataBaseDao.password = password;
	}

	public static void setUrl(String url) {
		DataBaseDao.url = url;
	}

	public DataBaseDao() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO 自动生成的构造函数存根
		Class.forName(drive).newInstance();// 加载驱动
		connection = DriverManager.getConnection(url, userName, password);// 获取数据库连接
		statement = connection.createStatement();// 获取statement对象
	}

	public void query(String sql) throws SQLException {// 数据库查询
		System.out.println(sql);
		resultSet = statement.executeQuery(sql);//
	}

	public Integer update(String sql) throws SQLException {// 数据库更新，插入，删除
		return statement.executeUpdate(sql);
	}

	public Boolean next() throws SQLException {
		return resultSet.next();
	}

	// 获取resultSet指定字段的值

	public String getString(String columnLabel) throws SQLException {
		return resultSet.getString(columnLabel);
	}

	public Integer getInt(String columnLabel) throws SQLException {
		return resultSet.getInt(columnLabel);
	}

	public Timestamp getTimestamp(String columnLabel) throws SQLException {
		return resultSet.getTimestamp(columnLabel);
	}

	public LocalDateTime getLocalDateTime(String field) throws SQLException {// 获取日期时间类型字段的值
		return resultSet.getTimestamp(field).toLocalDateTime();
	}

	//
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public static String getDrive() {
		return drive;
	}

	public static String getUrl() {
		return url;
	}

	public static String getUserName() {
		return userName;
	}

	public static String getPassword() {
		return password;
	}

	public Integer getCount(String sql) throws SQLException {// 查询符合条件的记录的数目
		query(sql);
		while (next()) {
			return this.getResultSet().getInt("count");
		}
		return 0;
	}

	public void setAutoCommit(boolean f) throws SQLException {
		connection.setAutoCommit(f);
	}

	public void commit() throws SQLException {
		connection.commit();
	}

}

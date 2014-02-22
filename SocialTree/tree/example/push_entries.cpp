#include<stdio.h>
#include<sqlite3.h>
#include<stdlib.h>
#include<string.h>
#include<iostream>
//#include <my_global.h>
//#include <mysql.h>
   int main()
	{
    // Create an int variable for storing the return code for each call
    int retval;

    std::string query;

    // The number of queries to be handled,size of each query and pointer
    //int q_cnt = 500,q_size = 150,ind = 0;
    //char **queries = (char**)malloc(sizeof(char) * q_cnt * q_size);

    // A prepered statement for fetching tables
    sqlite3_stmt *stmt;

    // Create a handle for database connection, create a pointer to sqlite3
    sqlite3 *handle,*handle1;

    // try to create the database. If it doesnt exist, it would be created
    // pass a pointer to the pointer to sqlite3, in short sqlite3**
    retval = sqlite3_open("roots.sqlite3",&handle);
    // If connection failed, handle returns NULL
    ////////////////////////////////////////////////////////
    char *table = (char*)malloc(100);
    table = "DROP TABLE IF EXISTS username";
    retval = sqlite3_exec(handle,table,0,0,0);
    table = "DROP TABLE IF EXISTS people";
    retval = sqlite3_exec(handle,table,0,0,0);
    table = "DROP TABLE IF EXISTS result";
    retval = sqlite3_exec(handle,table,0,0,0);
    table = "DROP TABLE IF EXISTS relations";
    retval = sqlite3_exec(handle,table,0,0,0);
    ////////////////////////////////////////////////////////
    if(retval)
    {
        printf("Database connection failed\n");
        return -1;
    }

    // Create the SQL query for creating a table
    char create_table1[100] = "CREATE TABLE IF NOT EXISTS username (emailid TEXT PRIMARY KEY,pass TEXT NOT NULL)";

    // Execute the query for creating the table
    retval = sqlite3_exec(handle,create_table1,0,0,0);

    // Insert usernames************************************************************************
    query = "INSERT INTO username VALUES('navi','navi')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO username VALUES('bob','bob')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);

    query = "INSERT INTO username VALUES('harcharan','harcharan')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO username VALUES('gyan','gyan')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);

    query = "INSERT INTO username VALUES('bind','bind')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO username VALUES('kuldeep','kuldeep')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);

    query = "INSERT INTO username VALUES('tony','tony')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO username VALUES('ravi','ravi')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);

    query = "INSERT INTO username VALUES('sumeet','sumeet')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO username VALUES('yuvi','yuvi')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    // Insert usernames************************************************************************;

    char create_table2[200] = "CREATE TABLE IF NOT EXISTS people (emailid TEXT PRIMARY KEY,name TEXT NOT NULL,dob BLOB,sex TEXT NOT NULL,online INTEGER)";

    // Execute the query for creating the table
    retval = sqlite3_exec(handle,create_table2,0,0,0);

    //ind=0;
    query = "INSERT INTO people VALUES('harcharan','harcharan',NULL,'m',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('bind','balwinder',NULL,'m',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('kuldeep','kuldeep kaur',NULL,'f',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('gyan','gyan kaur',NULL,'f',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('navi','nav dhaliwal',NULL,'m',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('bob','manreet kaur',NULL,'f',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('tony','harjinder sandhu',NULL,'m',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('ravi','raghujit kaur',NULL,'f',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('sumeet','sumeet sandhu',NULL,'m',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO people VALUES('yuvi','yuvraj',NULL,'m',0)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);

    sqlite3_int64 val = sqlite3_last_insert_rowid(handle);
    //retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    //////////////////////////////////////////////////////////////////////////////////
//Insert at run time;
char create_table3[200] = "CREATE TABLE IF NOT EXISTS result (emailid1 TEXT,emailid2 TEXT,res BLOB)";

    // Execute the query for creating the table
    retval = sqlite3_exec(handle,create_table3,0,0,0);

//*************************************************************************************

    char create_table4[200] = "CREATE TABLE IF NOT EXISTS relations (emailid1 TEXT NOT NULL,emailid2 TEXT NOT NULL,relation1 BLOB,relation2 BLOB,activated INTEGER)";

    // Execute the query for creating the table
    retval = sqlite3_exec(handle,create_table4,0,0,0);
    //ind = 0;
    query = "INSERT INTO relations VALUES('yuvi','sumeet','younger brother','older brother',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('kuldeep','bind','wife','husband',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('ravi','tony','wife','husband',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('bob','navi','sister','brother',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('gyan','harcharan','wife','husband',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('harcharan','bind','father','fathers son',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('gyan','bind','mother','mothers son',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('bind','navi','father','son',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('bind','bob','father','daughter',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('kuldeep','navi','mother','son',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('kuldeep','bob','mother','daughter',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('tony','sumeet','father','son',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('ravi','sumeet','mother','son',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('tony','yuvi','father','son',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('ravi','yuvi','mother','son',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
    query = "INSERT INTO relations VALUES('ravi','kuldeep','younger sister','older sister',1)";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);

    //Insert at run time;
char create_table5[200] = "CREATE TABLE IF NOT EXISTS address (emailid TEXT PRIMARY KEY,adr INTEGER)";

    // Execute the query for creating the table
    retval = sqlite3_exec(handle,create_table5,0,0,0);

//*************************************************************************************

// Close the handle to free memory
    sqlite3_close(handle);
    system("copy C:\\Users\\Dhaliwal\\Desktop\\createDB\\roots.sqlite3 C:\\Users\\Dhaliwal\\Desktop\\server\\roots.sqlite3");
    system("copy C:\\Users\\Dhaliwal\\Desktop\\createDB\\roots.sqlite3 C:\\wamp\\www\\roots.sqlite3");

}

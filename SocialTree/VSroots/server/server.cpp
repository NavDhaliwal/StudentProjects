//--------------------------------------------------------------------------//
/*************************** Server ********************/
//
// Module Name: Server.c
//
// Description:
//    This example illustrates a simple TCP server that accepts
//    incoming client connections. Once a client connection is
//    established, a thread is spawned to read data from the
//    client and echo it back (if the echo option is not
//    disabled).
//
// Compile:
//    cl -o Server Server.c ws2_32.lib
//
// Command line options:
//    server [-p:x] [-i:IP] [-o]
//           -p:x      Port number to listen on
//           -i:str    Interface to listen on
//           -o        Receive only, don't echo the data back
//
#include <winsock2.h>

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <sqlite3.h>
#include <roots_structs.h>
#include<iostream>
#include <list>
#define DEFAULT_PORT        5150
#define DEFAULT_BUFFER      4096

int    iPort      = DEFAULT_PORT; // Port to listen for clients on
BOOL   bInterface = FALSE,	 // Listen on the specified interface
       bRecvOnly  = FALSE;   // Receive data only; don't echo back
char   szAddress[128];       // Interface to listen for clients on

//
// Function: usage
//
// Description:
//    Print usage information and exit
//
void usage()
{
    printf("usage: server [-p:x] [-i:IP] [-o]\n\n");
    printf("       -p:x      Port number to listen on\n");
    printf("       -i:str    Interface to listen on\n");
    printf("       -o        Don't echo the data back\n\n");
    ExitProcess(1);
}
/////////
//Globals
int retval;

    // The number of queries to be handled,size of each query and pointer
    //int q_cnt = 5,q_size = 150,ind = 0;
    //char **queries = (char**)malloc(sizeof(char) * q_cnt * q_size);


    // A prepered statement for fetching tables


    // Create a handle for database connection, create a pointer to sqlite3
    sqlite3 *handle;


//////////
person* getPersonPointer(const char* id)
{

std::string query;
    char qtemp[100];
    sprintf(qtemp,"SELECT * from address where emailid = \'%s\'",id);
    query = qtemp;
    sqlite3_stmt *stmt;

    retval = sqlite3_prepare_v2(handle,query.c_str(),-1,&stmt,0);
    if(retval)
    {
        printf("Selecting data from DB Failed\n");
        return NULL;
    }

    // Read the number of rows fetched
    int cols = sqlite3_column_count(stmt);

    while(1)
    {
        // fetch a row's status
        retval = sqlite3_step(stmt);

        if(retval == SQLITE_ROW)
        {
            // SQLITE_ROW means fetched a row

            // sqlite3_column_text returns a const void* , typecast it to const char*
            for(int col=0 ; col<cols;col++)
            {

                //printf("%s = %s\t",sqlite3_column_name(stmt,col),val.c_str());
                if(!strcmp("adr",sqlite3_column_name(stmt,col)))
                {
                    unsigned __int64 t=(unsigned __int64)sqlite3_column_int64(stmt,col);
                    person* ptr;
                    ptr =(person*)(t);
                    return ptr;
                    //if(ptr!=NULL)
                        //printf("**************%s************",ptr->name.c_str());
                }
            }

        }
    }
}
/////////

//
// Function: ValidateArgs
//
// Description:
//    Parse the command line arguments, and set some global flags
//    to indicate what actions to perform
//
void ValidateArgs(int argc, char **argv)
{
    int i;

    for(i = 1; i < argc; i++)
    {
        if ((argv[i][0] == '-') || (argv[i][0] == '/'))
        {
            switch (tolower(argv[i][1]))
            {
                case 'p':
                    iPort = atoi(&argv[i][3]);
                    break;
                case 'i':
                    bInterface = TRUE;
                    if (strlen(argv[i]) > 3)
                        strcpy(szAddress, &argv[i][3]);
                    break;
                   case 'o':
		           bRecvOnly = TRUE;
                       break;
                default:
                    usage();
                    break;
            }
        }
    }
}

//
// Function: ClientThread
//
// Description:
//    This function is called as a thread, and it handles a given
//    client connection.  The parameter passed in is the socket
//    handle returned from an accept() call.  This function reads
//    data from the client and writes it back.
//

//struct for shortest path. When a node pops from queue, its pushed on the tree.
struct tree
{
	person* personPtr;
	std::vector<struct tree*> children;
	struct tree* parent;
	std::string linkname;
	tree()
	{
	    personPtr = NULL;
	    parent = NULL;
	}
};
struct SParent
{
   tree* TreeNode;
   int NumChildren;

};
struct SLinkname
{
  std::string linkname;
};
bool CheckQ(std::list<person*>& q,person* ptr)
{
    bool res = false;
    person* tmp;
    int size = q.size();
    for(int i = 0;i<size;i++)
    {
        tmp = q.front();
        printf("\n\nChecking Q : %s  %s",tmp->name.c_str(),ptr->name.c_str());
        if(tmp==ptr)
            res = true;
        q.pop_front();
        q.push_back(tmp);
    }
    return res;

}
void CheckTree(tree* TreeRoot,person* ptr,bool& set)
{
    printf("\n\nChecking tree  Now ptr %s = To check %s\n\n",TreeRoot->personPtr->name.c_str(),ptr->name.c_str());
    if(TreeRoot->personPtr==ptr)
        set = true;
    if(TreeRoot == NULL)
        return;
    int i = TreeRoot->children.size();
    while(i>0)
    {
        i--;
        CheckTree(TreeRoot->children[i],ptr,set);

    }
}
void printLinknameList(std::list<SLinkname*>& linknameList)
{
    SLinkname* tmp;
    printf("\n\nLINK name List : \n\n");
    int i=linknameList.size();
    while(i>0)
    {
        i--;
        tmp = linknameList.front();
        linknameList.pop_front();
        linknameList.push_back(tmp);
        printf(" %s ",tmp->linkname.c_str());
    }
}
void printQ(std::list<person*>& q)
{
    person* tmp ;
    printf("\n\nQ : \n\n");
    int i=q.size();
    while(i>0)
    {
        i--;
        tmp = q.front();
        q.pop_front();
        q.push_back(tmp);
        printf(" %s ",tmp->name.c_str());
    }

}
void printParentList(std::list<SParent*>& ParentList)
{
    SParent* tmp ;
    printf("\n\nParent List : \n\n");
    int i=ParentList.size();
    while(i>0)
    {
        i--;
        tmp = ParentList.front();
        ParentList.pop_front();
        ParentList.push_back(tmp);
        printf(" %s  %d",tmp->TreeNode->personPtr->name.c_str(),tmp->NumChildren);
    }

}

void MakeTree(std::list<SParent*>& ParentList,tree* TreeRoot,std::list<person*>& q,std::list<SLinkname*>& linknameList,char* emailid2,tree* &result)
{
    printQ(q);
    printParentList(ParentList);
    tree* TreePtr;
    int flag = 0;
    int i=0;
    int numChildren = 0;
    person* PoppedPerson=NULL;
    person* childPerson=NULL;
    SParent* ParentNode=NULL;
    SLinkname* plinkname = NULL;
    std::string linkname = "";
    while(1)
    {
        //check in tree for emailid2
        //check in q for emailid2

        //Pop from q; if popped person = to find, break;
        PoppedPerson = q.front();
		q.pop_front();
        printf("\n\nPopped Person = %s\n\n",PoppedPerson->name.c_str());

		//Decrement parent list
//		if(ParentList.at(0)->NumChildren>0)
//            ParentList.at(0)->NumChildren--;
//		else
//		{
//		    ParentList.pop_front();
//		    ParentList.at(0)->NumChildren--;
//		}
        if(ParentList.front()->NumChildren>0)
            ParentList.front()->NumChildren--;
		else
		{
		    while(ParentList.front()->NumChildren == 0)
		    {

                ParentList.pop_front();
		    }
            ParentList.front()->NumChildren--;
		}

        printParentList(ParentList);
        //make tree node and inter connect
        plinkname= linknameList.front();
        linknameList.pop_front();
		TreePtr = new tree();
		TreePtr->personPtr = PoppedPerson;
		TreePtr->parent = ParentList.front()->TreeNode;
		TreePtr->linkname = plinkname->linkname;
		ParentList.front()->TreeNode->children.push_back(TreePtr);
		printParentList(ParentList);

        printLinknameList(linknameList);
		if(PoppedPerson->id == emailid2)
		{
		    result = TreePtr;
		    break;
		}

//TODO find if already in q;if yes dont put in q and dont add in num children
//also check in the exsisting tree, dont put if already parsed;
		//find children and put in q
		numChildren = PoppedPerson->links.size();
		i = 0;
		int childrenParsed = 0;
		while(i<numChildren)
        {
            if(PoppedPerson->links.at(i)->right == PoppedPerson)
            {
                childPerson = PoppedPerson->links.at(i)->left;
                linkname = PoppedPerson->links.at(i)->left_relation;
            }
		    else
		    {
                childPerson = PoppedPerson->links.at(i)->right;
                linkname = PoppedPerson->links.at(i)->right_relation;
		    }

            bool parsed = false;
            printQ(q);
            printParentList(ParentList);
            parsed = CheckQ(q,childPerson);
            printQ(q);
            if(!parsed)
                CheckTree(TreeRoot,childPerson,parsed);
            if(parsed)
            {
                childrenParsed++;
            }
            else
            {
                q.push_back(childPerson);
                plinkname = new SLinkname();
                plinkname->linkname = linkname;
                linknameList.push_back(plinkname);
            }
            i++;
        }


        printLinknameList(linknameList);
        printQ(q);
        printParentList(ParentList);
        //create parent struct node
        ParentNode = new SParent();
        ParentNode->NumChildren = numChildren - childrenParsed;
        ParentNode->TreeNode=TreePtr;

        //push in ParentList
        ParentList.push_back(ParentNode);
        printQ(q);
        printParentList(ParentList);
    }

}
void printFullPath(tree* result)
{
    printf("\n\nFull Path : \n\n");
while(result!=NULL)
{
printf(" %s -> ",result->personPtr->name.c_str());
printf(" %s -> ",result->linkname.c_str());
result = result->parent;
}
}
void fillDB(tree* result,tree* TreeRoot)
{
    std::string query;
    sqlite3_stmt *stmt;
    //retval = sqlite3_open("roots.sqlite3",&handle);
    //result (emailid1 TEXT,emailid2 TEXT,res BLOB)";
std::string res="";
std::string emailid1 = TreeRoot->personPtr->id;
std::string emailid2 = result->personPtr->id;
while(result!=NULL)
{
res+=result->personPtr->name;
res+=" is ";
res+=result->linkname;
res+=" of ";
result = result->parent;
}
    //ind = 0;

    query = "INSERT INTO result VALUES('"+emailid1+"','"+emailid2+"','"+res+"')";
    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
}
DWORD WINAPI ClientThread(LPVOID lpParam)
{
    SOCKET        sock=(SOCKET)lpParam;
    char          szBuff[DEFAULT_BUFFER];
    int           ret,
                  nLeft,
                  idx;
	tree* TreePtr;

    //while(1)
    {
        // Perform a blocking recv() call
        //
        ret = recv(sock, szBuff, DEFAULT_BUFFER, 0);
        if (ret == 0)        // Graceful close
            return ret;//break;
        else if (ret == SOCKET_ERROR)
        {
            printf("recv() failed: %d\n", WSAGetLastError());
            return ret;//break;
        }
        szBuff[ret] = '\0';
        printf("RECV: '%s'\n", szBuff);
		char* emailid1 = strtok(szBuff,",");
		char* emailid2 = strtok(NULL,",");
		printf("\n\nEmail id 1 = %s\n\n",emailid1);
		printf("\n\nEmail id 2 = %s\n\n",emailid2);
		std::list<person*> q;
		std::list<SLinkname*> linknameList;
		//put node in queue;
		person* PersonPtr = getPersonPointer(emailid1);
		q.push_back(PersonPtr);
		TreePtr = new tree();
		TreePtr->parent = NULL;// for emailid 1 only;
		person* PoppedPerson = q.front();
		q.pop_front();
		TreePtr->personPtr = PoppedPerson;
		int numChildren = PoppedPerson->links.size();
		int i=0;
		std::string linkname = "";
		SLinkname* plinkname;
		while(i<numChildren)
		{
		    person* childPerson;
		    if(PoppedPerson->links.at(i)->right == PoppedPerson)
		    {
                childPerson = PoppedPerson->links.at(i)->left;
                linkname=PoppedPerson->links.at(i)->left_relation;
		    }
		    else
		    {
                childPerson = PoppedPerson->links.at(i)->right;
                linkname=PoppedPerson->links.at(i)->right_relation;
		    }
		    plinkname = new SLinkname();
		    plinkname->linkname = linkname;
		    linknameList.push_back(plinkname);
		    q.push_back(childPerson);
		    i++;
		}
		SParent* ParentNode=new SParent();
		ParentNode->NumChildren=numChildren;
		ParentNode->TreeNode=TreePtr;
		std::list<SParent*> ParentList;
		ParentList.push_back(ParentNode);
		tree* result=NULL;
		printLinknameList(linknameList);
		printQ(q);
		MakeTree(ParentList,TreePtr,q,linknameList,emailid2,result);
		printf("\n\nresult = %s\n\n",result->personPtr->name.c_str());
		printFullPath(result);
		fillDB(result,TreePtr);
        //
        // If we selected to echo the data back, do it
        //
        if (!bRecvOnly)
        {
            nLeft = ret;
            idx = 0;
			//
            // Make sure we write all the data
            //
            while(nLeft > 0)
            {
                ret = send(sock, &szBuff[idx], nLeft, 0);
                if (ret == 0)
                    break;
                else if (ret == SOCKET_ERROR)
                {
                    printf("send() failed: %d\n",
                        WSAGetLastError());
                    break;
                }
                nLeft -= ret;
                idx += ret;
               }
        }
    }
    return 0;
}
//
// Function: main
//
// Description:
//    Main thread of execution. Initialize Winsock, parse the
//    command line arguments, create the listening socket, bind
//    to the local address, and wait for client connections.
//
int main(int argc, char **argv)
{
    WSADATA       wsd;
    SOCKET        sListen,
                  sClient;
    int           iAddrSize;
    HANDLE        hThread;
    DWORD         dwThreadId;
    struct sockaddr_in local,
                       client;

std::string query;
    person* ptop;
    sqlite3_stmt *stmt;
    unsigned __int64 t1,t2;
    ValidateArgs(argc, argv);

    // Create an int variable for storing the return code for each call


    // try to create the database. If it doesnt exist, it would be created
    // pass a pointer to the pointer to sqlite3, in short sqlite3**
    retval = sqlite3_open("C:\\Users\\Dhaliwal\\Desktop\\createDB\\roots.sqlite3",&handle);
    // If connection failed, handle returns NULL

    char *table = (char*)malloc(100);
    table = "DELETE from address";
    retval = sqlite3_exec(handle,table,0,0,0);
    //for all people

    query = "SELECT Count(*) FROM people";
    retval = sqlite3_prepare_v2(handle,query.c_str(),-1,&stmt,0);
    // select those rows from the table
    query = "SELECT * from people";
    retval = sqlite3_prepare_v2(handle,query.c_str(),-1,&stmt,0);
    if(retval)
    {
        printf("Selecting data from DB Failed\n");
        return -1;
    }

    // Read the number of rows fetched
    int cols = sqlite3_column_count(stmt);

    while(1)
    {
        // fetch a row's status
        retval = sqlite3_step(stmt);

        if(retval == SQLITE_ROW)
        {
            // SQLITE_ROW means fetched a row

            ptop = new person;
            std::string val;
            // sqlite3_column_text returns a const void* , typecast it to const char*
            for(int col=0 ; col<cols;col++)
            {
                const char* tmp=(const char*)sqlite3_column_text(stmt,col);
                if(tmp!=NULL)
                val = tmp;
                //printf("%s = %s\t",sqlite3_column_name(stmt,col),val.c_str());
                if(!strcmp("emailid",sqlite3_column_name(stmt,col)))
                {
                    ptop->id=val;
                    char qtemp[100];
                    t1 = (unsigned __int64)ptop;
                    sprintf(qtemp,"INSERT INTO address VALUES(\"%s\",%llu)",val.c_str(),t1);
                    query = qtemp;
                    printf("\n\n%s\n\n",val.c_str());

                    //printf("\n\n\n\n%d\n\n\n\n",sizeof(unsigned int));
                    //queries[ind++] = "INSERT INTO address VALUES(val,ptop)";
                    retval = sqlite3_exec(handle,query.c_str(),0,0,0);
                }
                else if(!strcmp("name",sqlite3_column_name(stmt,col)))
                {
                    ptop->name=val;
                }
            }
            printf("\n");
        }
        else if(retval == SQLITE_DONE)
        {
            // All rows finished
            printf("All rows fetched\n");
            break;
        }
        else
        {
            // Some error encountered
            printf("Some error encountered\n");
            return -1;
        }
    }


    ////////////////////////RELATIONS////////////////////////////////
    query = "SELECT * from relations";
    retval = sqlite3_prepare_v2(handle,query.c_str(),-1,&stmt,0);
    if(retval)
    {
        printf("Selecting data from DB Failed\n");
        return -1;
    }

    // Read the number of rows fetched
    cols = sqlite3_column_count(stmt);

    while(1)
    {
        // fetch a row's status
        retval = sqlite3_step(stmt);

        relation* relationPtr;
        if(retval == SQLITE_ROW)
        {
            // SQLITE_ROW means fetched a row

            relationPtr=new relation;
            // sqlite3_column_text returns a const void* , typecast it to const char*
            for(int col=0 ; col<cols;col++)
            {

                //printf("%s = %s\t",sqlite3_column_name(stmt,col),val.c_str());
                if(!strcmp("emailid1",sqlite3_column_name(stmt,col)))
                {
                    const char* id=(const char*)sqlite3_column_text(stmt,col);
                    relationPtr->left = getPersonPointer(id);
                    relationPtr->left->links.push_back(relationPtr);
                }
                else if(!strcmp("emailid2",sqlite3_column_name(stmt,col)))
                {
                    const char* id=(const char*)sqlite3_column_text(stmt,col);
                    relationPtr->right = getPersonPointer(id);
                    relationPtr->right->links.push_back(relationPtr);
                }
                else if(!strcmp("relation1",sqlite3_column_name(stmt,col)))
                {
                    relationPtr->left_relation=(const char*)sqlite3_column_text(stmt,col);

                }
                else if(!strcmp("relation2",sqlite3_column_name(stmt,col)))
                {
                    relationPtr->right_relation=(const char*)sqlite3_column_text(stmt,col);

                }
            }

        }
        else if(retval == SQLITE_DONE)
        {
            // All rows finished
            printf("All relations created\n");
            break;
        }
        else
        {
            // Some error encountered
            printf("Some error encountered while creating relations\n");
            return -1;
        }
    }
    //////////////////////////RELATIONS///////////////////////////////
    ////////////////////////////////////////////////////////


    if (WSAStartup(MAKEWORD(2,2), &wsd) != 0)
    {
        printf("Failed to load Winsock!\n");
        return 1;
    }
    // Create our listening socket
    //
    sListen = socket(AF_INET, SOCK_STREAM, IPPROTO_IP);
    if (sListen == SOCKET_ERROR)
    {
        printf("socket() failed: %d\n", WSAGetLastError());
        return 1;
    }
    // Select the local interface and bind to it
    //
    if (bInterface)
    {
        local.sin_addr.s_addr = inet_addr(szAddress);
        if (local.sin_addr.s_addr == INADDR_NONE)
            usage();
    }
    else
        local.sin_addr.s_addr = htonl(INADDR_ANY);
    local.sin_family = AF_INET;
    local.sin_port = htons(iPort);

    if (bind(sListen, (struct sockaddr *)&local,
            sizeof(local)) == SOCKET_ERROR)
    {
        printf("bind() failed: %d\n", WSAGetLastError());
        return 1;
    }
    listen(sListen, 8);
    //
    // In a continous loop, wait for incoming clients. Once one
    // is detected, create a thread and pass the handle off to it.
    //
    while (1)
    {
        iAddrSize = sizeof(client);
        sClient = accept(sListen, (struct sockaddr *)&client,
                        &iAddrSize);
        if (sClient == INVALID_SOCKET)
        {
            printf("accept() failed: %d\n", WSAGetLastError());
            break;
        }
        printf("Accepted client: %s:%d\n",
            inet_ntoa(client.sin_addr), ntohs(client.sin_port));

        hThread = CreateThread(NULL, 0, ClientThread,
                    (LPVOID)sClient, 0, &dwThreadId);
        if (hThread == NULL)
        {
            printf("CreateThread() failed: %d\n", GetLastError());
            break;
        }
        CloseHandle(hThread);
    }
    closesocket(sListen);

    WSACleanup();
    return 0;
}

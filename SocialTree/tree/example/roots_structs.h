#include<iostream>
#include <string.h>
#include <vector.h>
using namespace std;
struct _relation;
typedef struct _person
{
    std::string name;
    std::string id;
    std::vector<struct _relation*> links;
}person;

typedef struct _relation
{
    person* left;
    std::string left_relation;
    person* right;
    std::string right_relation;
}relation;

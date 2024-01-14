#include <iostream>

using namespace std;

static int N,X;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> N >> X;
	int input;
	for(int i=0;i<N;i++){
		cin >> input;
		if(X > input){
			cout << input << ' ';
		}
	}
	return 0;
}
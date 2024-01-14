#include <iostream>

using namespace std;

int v[26];

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);

	string s;
	cin >> s;
	for(auto e: s){
		v[e-'a'] += 1;
	}
	for(int i=0;i<26;i++){
		cout << v[i] << ' ';
	}
}
package class175;

// 哈希冲突，C++版
// 测试链接 : https://www.luogu.com.cn/problem/P3396
// 如下实现是C++的版本，C++版本和java版本逻辑完全一样
// 提交如下代码，可以通过所有测试用例

//#include <bits/stdc++.h>
//
//using namespace std;
//
//const int MAXN = 150001;
//const int MAXB = 401;
//int n, m, blen;
//int arr[MAXN];
//long long dp[MAXB][MAXB];
//
//long long query(int x, int y) {
//    if (x <= blen) {
//        return dp[x][y];
//    }
//    long long ans = 0;
//    for (int i = y; i <= n; i += x) {
//        ans += arr[i];
//    }
//    return ans;
//}
//
//void update(int x, int y) {
//    int delta = y - arr[x];
//    arr[x] = y;
//    for (int mod = 1; mod <= blen; mod++) {
//        dp[mod][x % mod] += delta;
//    }
//}
//
//void prepare() {
//    blen = (int)sqrt(n);
//    for (int mod = 1; mod <= blen; mod++) {
//        for (int i = 1; i <= n; i++) {
//            dp[mod][i % mod] += arr[i];
//        }
//    }
//}
//
//int main() {
//    ios::sync_with_stdio(false);
//    cin.tie(nullptr);
//    cin >> n >> m;
//    for (int i = 1; i <= n; i++) {
//        cin >> arr[i];
//    }
//    prepare();
//    char op;
//    int x, y;
//    for (int i = 1; i <= m; i++) {
//        cin >> op >> x >> y;
//        if (op == 'A') {
//            cout << query(x, y) << '\n';
//        } else {
//            update(x, y);
//        }
//    }
//    return 0;
//}
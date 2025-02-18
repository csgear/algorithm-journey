package class160;

// 线段树套线段树，C++版
// 人有三种属性，身高、活泼度、缘分值
// 身高为int类型，活泼度和缘分值为小数点后最多1位的double类型
// 实现一种结构，提供如下两种类型的操作
// 操作 I a b c   : 加入一个人，身高为a，活泼度为b，缘分值为c
// 操作 Q a b c d : 查询身高范围[a,b]，活泼度范围[c,d]，所有人中的缘分最大值
// 注意操作Q，如果a > b需要交换，如果c > d需要交换
// 100 <= 身高 <= 200
// 0.0 <= 活泼度、缘分值 <= 100.0
// 测试链接 : https://acm.hdu.edu.cn/showproblem.php?pid=1823
// 如下实现是C++的版本，C++版本和java版本逻辑完全一样
// 提交如下代码，可以通过所有测试用例

//#include <bits/stdc++.h>
//
//using namespace std;
//
//const int MAXH = 101;
//const int MAXA = 1001;
//int n = 1000, m;
//int tree[MAXH << 2][MAXA << 2];
//
//void innerBuild(int yl, int yr, int xi, int yi) {
//    tree[xi][yi] = -1;
//    if (yl < yr) {
//        int mid = (yl + yr) >> 1;
//        innerBuild(yl, mid, xi, yi << 1);
//        innerBuild(mid + 1, yr, xi, yi << 1 | 1);
//    }
//}
//
//void innerUpdate(int joby, int jobv, int yl, int yr, int xi, int yi) {
//    if (yl == yr) {
//        tree[xi][yi] = max(tree[xi][yi], jobv);
//    } else {
//        int mid = (yl + yr) >> 1;
//        if (joby <= mid) {
//        	innerUpdate(joby, jobv, yl, mid, xi, yi << 1);
//        } else {
//        	innerUpdate(joby, jobv, mid + 1, yr, xi, yi << 1 | 1);
//        }
//        tree[xi][yi] = max(tree[xi][yi << 1], tree[xi][(yi << 1) | 1]);
//    }
//}
//
//int innerQuery(int jobyl, int jobyr, int yl, int yr, int xi, int yi) {
//    if (jobyl <= yl && yr <= jobyr) {
//        return tree[xi][yi];
//    }
//    int mid = (yl + yr) >> 1;
//    int ans = -1;
//    if (jobyl <= mid) {
//        ans = max(ans, innerQuery(jobyl, jobyr, yl, mid, xi, yi << 1));
//    }
//    if (jobyr > mid) {
//        ans = max(ans, innerQuery(jobyl, jobyr, mid + 1, yr, xi, (yi << 1) | 1));
//    }
//    return ans;
//}
//
//void outerBuild(int xl, int xr, int xi) {
//	innerBuild(0, n, xi, 1);
//    if (xl < xr) {
//        int mid = (xl + xr) >> 1;
//        outerBuild(xl, mid, xi << 1);
//        outerBuild(mid + 1, xr, xi << 1 | 1);
//    }
//}
//
//void outerUpdate(int jobx, int joby, int jobv, int xl, int xr, int xi) {
//	innerUpdate(joby, jobv, 0, n, xi, 1);
//    if (xl < xr) {
//        int mid = (xl + xr) >> 1;
//        if (jobx <= mid) {
//        	outerUpdate(jobx, joby, jobv, xl, mid, xi << 1);
//        } else {
//        	outerUpdate(jobx, joby, jobv, mid + 1, xr, xi << 1 | 1);
//        }
//    }
//}
//
//int outerQuery(int jobxl, int jobxr, int jobyl, int jobyr, int xl, int xr, int xi) {
//    if (jobxl <= xl && xr <= jobxr) {
//        return innerQuery(jobyl, jobyr, 0, n, xi, 1);
//    }
//    int mid = (xl + xr) >> 1;
//    int ans = -1;
//    if (jobxl <= mid) {
//        ans = max(ans, outerQuery(jobxl, jobxr, jobyl, jobyr, xl, mid, xi << 1));
//    }
//    if (jobxr > mid) {
//        ans = max(ans, outerQuery(jobxl, jobxr, jobyl, jobyr, mid + 1, xr, (xi << 1) | 1));
//    }
//    return ans;
//}
//
//int main() {
//	scanf("%d", &m);
//	while(m != 0) {
//		outerBuild(100, 200, 1);
//        for (int i = 0; i < m; i++) {
//        	char op[2];
//            scanf("%s", op);
//            if (op[0] == 'I') {
//                int a;
//                double bd, cd;
//                scanf("%d %lf %lf", &a, &bd, &cd);
//                int b = (int)(bd * 10);
//                int c = (int)(cd * 10);
//                outerUpdate(a, b, c, 100, 200, 1);
//            } else {
//                int a, b;
//                double cd, dd;
//                scanf("%d %d %lf %lf", &a, &b, &cd, &dd);
//                int c = (int)(cd * 10);
//                int d = (int)(dd * 10);
//                int xl = min(a, b);
//                int xr = max(a, b);
//                int yl = min(c, d);
//                int yr = max(c, d);
//                int ans = outerQuery(xl, xr, yl, yr, 100, 200, 1);
//                if (ans == -1) {
//                    printf("-1\n");
//                } else {
//                    printf("%.1f\n", ans / 10.0);
//                }
//            }
//        }
//        scanf("%d", &m);
//	}
//    return 0;
//}
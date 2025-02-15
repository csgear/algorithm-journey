package class160;

// 排队，java版
// 测试链接 : https://www.luogu.com.cn/problem/P1975
// 提交以下的code，提交时请把类名改成"Main"，可以通过所有测试用例

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code04_LineUp1 {

	public static int MAXN = 20001;

	public static int MAXT = MAXN * 80;

	public static int INF = 1000000001;

	public static int n, m, s;

	public static int[] arr = new int[MAXN];

	public static int[] sorted = new int[MAXN + 2];

	public static int[] root = new int[MAXN];

	public static int[] left = new int[MAXT];

	public static int[] right = new int[MAXT];

	public static int[] sum = new int[MAXT];

	public static int cnt = 0;

	public static int ans = 0;

	public static int kth(int num) {
		int left = 1, right = s, mid;
		while (left <= right) {
			mid = (left + right) / 2;
			if (sorted[mid] == num) {
				return mid;
			} else if (sorted[mid] < num) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

	public static int lowbit(int i) {
		return i & -i;
	}

	public static int add(int jobi, int jobv, int l, int r, int i) {
		if (i == 0) {
			i = ++cnt;
		}
		if (l == r) {
			sum[i] += jobv;
		} else {
			int mid = (l + r) / 2;
			if (jobi <= mid) {
				left[i] = add(jobi, jobv, l, mid, left[i]);
			} else {
				right[i] = add(jobi, jobv, mid + 1, r, right[i]);
			}
			sum[i] = sum[left[i]] + sum[right[i]];
		}
		return i;
	}

	public static int query(int jobl, int jobr, int l, int r, int i) {
		if (i == 0) {
			return 0;
		}
		if (jobl <= l && r <= jobr) {
			return sum[i];
		}
		int mid = (l + r) / 2;
		int ans = 0;
		if (jobl <= mid) {
			ans += query(jobl, jobr, l, mid, left[i]);
		}
		if (jobr > mid) {
			ans += query(jobl, jobr, mid + 1, r, right[i]);
		}
		return ans;
	}

	public static void insert(int i, int v) {
		for (int j = i; j <= n; j += lowbit(j)) {
			root[j] = add(arr[i], v, 1, s, root[j]);
		}
	}

	public static int queryCnt(int al, int ar, int numl, int numr) {
		int ans = 0;
		for (int i = ar; i > 0; i -= lowbit(i)) {
			ans += query(numl, numr, 1, s, root[i]);
		}
		for (int i = al - 1; i > 0; i -= lowbit(i)) {
			ans -= query(numl, numr, 1, s, root[i]);
		}
		return ans;
	}

	public static void prepare() {
		s = 0;
		for (int i = 1; i <= n; i++) {
			sorted[++s] = arr[i];
		}
		sorted[++s] = -INF;
		sorted[++s] = INF;
		Arrays.sort(sorted, 1, s + 1);
		int len = 1;
		for (int i = 2; i <= s; i++) {
			if (sorted[len] != sorted[i]) {
				sorted[++len] = sorted[i];
			}
		}
		s = len;
		for (int i = 1; i <= n; i++) {
			arr[i] = kth(arr[i]);
			insert(i, 1);
		}
	}

	public static void compute(int a, int b) {
		ans -= queryCnt(a + 1, b - 1, 1, arr[a] - 1);
		ans += queryCnt(a + 1, b - 1, arr[a] + 1, s);
		ans -= queryCnt(a + 1, b - 1, arr[b] + 1, s);
		ans += queryCnt(a + 1, b - 1, 1, arr[b] - 1);
		if (arr[a] < arr[b]) {
			ans++;
		} else if (arr[a] > arr[b]) {
			ans--;
		}
		insert(a, -1);
		insert(b, -1);
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
		insert(a, 1);
		insert(b, 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		for (int i = 1; i <= n; i++) {
			in.nextToken();
			arr[i] = (int) in.nval;
		}
		prepare();
		for (int i = 2; i <= n; i++) {
			ans += queryCnt(1, i - 1, arr[i] + 1, s);
		}
		out.println(ans);
		in.nextToken();
		m = (int) in.nval;
		for (int i = 1, a, b; i <= m; i++) {
			in.nextToken();
			a = (int) in.nval;
			in.nextToken();
			b = (int) in.nval;
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			compute(a, b);
			out.println(ans);
		}
		out.flush();
		out.close();
		br.close();
	}

}

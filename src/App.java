
public class App {
    public static void main(String[] args) throws Exception {
        // se crean los nodos o las ciudades
        Ciudad ciudadA = new Ciudad("A");
        Ciudad ciudadB = new Ciudad("B");
        Ciudad ciudadC = new Ciudad("C");
        Ciudad ciudadD = new Ciudad("D");
        Ciudad ciudadE = new Ciudad("E");
        Ciudad ciudadF = new Ciudad("F");
        Ciudad ciudadG = new Ciudad("G");
        Ciudad ciudadH = new Ciudad("H");

        // Se crean las relaciones entre las ciudades aplicandole una distancia en
        // especifico, entre más distancia, más lejos y el camino se vuelve más largo,
        // se podría altear alguna distancia como por ejemplo entre C y E y se tomará
        // otro camino

        ciudadA.conectar(ciudadB, 1);
        ciudadA.conectar(ciudadC, 1);
        ciudadB.conectar(ciudadA, 1);
        ciudadB.conectar(ciudadC, 1);
        ciudadB.conectar(ciudadD, 1);
        ciudadD.conectar(ciudadB, 1);
        ciudadD.conectar(ciudadE, 1);
        ciudadD.conectar(ciudadF, 1);
        ciudadC.conectar(ciudadA, 1);
        ciudadC.conectar(ciudadB, 1);
        ciudadC.conectar(ciudadE, 5);
        ciudadE.conectar(ciudadC, 1);
        ciudadE.conectar(ciudadD, 1);
        ciudadE.conectar(ciudadG, 1);
        ciudadG.conectar(ciudadH, 1);
        ciudadH.conectar(ciudadG, 1);

        Resultados R = new Resultados();
        Ciudad caminos[] = new Ciudad[100];
        HallarCaminos(ciudadA, ciudadH, caminos, 0, R, 0);

        R.Mostrar();
    }

    public static void HallarCaminos(Ciudad a, Ciudad b, Ciudad[] caminos, int distancia, Resultados R,
            int distanciaTotal) {
        boolean flag = true;
        int distanciaNodos = distancia;
        for (int i = 0; i < a.NumCaminos; i++) {
            for (int j = 0; j < distanciaNodos; j++) {

                if (caminos[j].nombre.equals(a.conexion[i].nombre)) {
                    flag = false;

                }

            }
            if (flag) {
                caminos[distanciaNodos] = a;
                HallarCaminos(a.conexion[i], b, caminos, distanciaNodos + 1, R, distanciaTotal + a.distancia[i]);
            }
            flag = true;
        }
        if (a.equals(b)) {
            caminos[distanciaNodos] = a;
            R.llenar(caminos, distanciaNodos, distanciaTotal);

        }

    }

    public static class Resultados {
        Ciudad caminosPosibles[][] = new Ciudad[50][50];
        int distancia[] = new int[50];
        int caminoNum = 0;
        int minimo[] = new int[5];
        int distanciaTotal[] = new int[50];

        public void llenar(Ciudad[] caminos, int distancia, int distanciaTotal) {
            for (int i = 0; i < distancia + 1; i++) {
                caminosPosibles[caminoNum][i] = caminos[i];
            }
            this.distancia[caminoNum] = distancia;
            this.distanciaTotal[caminoNum] = distanciaTotal;
            this.caminoNum = this.caminoNum + 1;
        }

        public void Minimo() {
            this.minimo[0] = distanciaTotal[0];
            this.minimo[1] = 0;
            for (int i = 0; i < caminoNum; i++) {
                if (distanciaTotal[i] < this.minimo[0]) {
                    this.minimo[0] = distanciaTotal[i];
                    this.minimo[1] = i;
                }
            }
        }

        public void Mostrar() {
            Minimo();
            System.out.println("Caminos totales: " + caminoNum);
            for (int i = 0; i < caminoNum; i++) {
                for (int j = 0; j < distancia[i] + 1; j++) {
                    System.out.print(caminosPosibles[i][j].nombre + " ");
                }
                System.out.println("");
            }
            System.out.println("Camino minimo:");
            for (int j = 0; j < distancia[minimo[1]] + 1; j++) {
                System.out.print(caminosPosibles[minimo[1]][j].nombre + " ");
            }
            System.out.println("");
            System.out.println("Distancia total minima:");
            System.out.println(minimo[0]);

            System.out.println("Nodos recorridos:");
            System.out.println(distancia[minimo[1]]);

        }
    }

    public static class Ciudad {
        String nombre;
        Ciudad conexion[] = new Ciudad[50];
        int distancia[] = new int[50];
        int NumCaminos;

        public Ciudad(String nombre) {
            this.nombre = nombre;
            NumCaminos = 0;
        }

        public void conectar(Ciudad ciudad, int distancia) {
            this.conexion[NumCaminos] = ciudad;
            this.distancia[NumCaminos] = distancia;
            NumCaminos++;

        }

    }
}

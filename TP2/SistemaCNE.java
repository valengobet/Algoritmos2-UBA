package aed;

import aed.SistemaCNE.VotosPartido;

import aed.T;
public class SistemaCNE {

    //INVARIANTE DE REPRESENTACIO
        //Todos los ints tienen que ser >= 0 (no hay valores negativos)
        // -Los elementos de nombresDePartidos son todos distintos(strings).
        // -Los elementos de NombresDeDistritos son todos distintos(strings).
        // -Long nombresDePartidos = P
        // -Long nombresDeDistritos = D
        // -Long bancasPorDistritos = D
        // -esCreciente(rangoMesas) y tiene longitud D
        // -Long votosPresidenciales = P
        // -#filas votosDiputados = D, #columnas votosDiputados = P, esMatriz(votosDiputados)
        // -0=<primero<P y 0=<segundo<P, y además primero != segundo.
        // -total_votos_presidente = sum(votosPresidenciales)
        // -sum(total_votos_diputados) = sum(resultadosDiputados) sumando por fila y columna(la suma de todos los elementos)
        // -#filas resultadosDiputados = D, #columnas resultadosDiputados = P, esMatriz(resultadosDiputados)
        // -#filas cocientesPorDistrito = D, #columnas cocientesPorDistrito = P, esMatriz(cocientesPorDistrito)
        // -seRepartioBancas es False si no se registraron los votos de ese distrito, y es True si ya se registraron.
    
    // Completar atributos privados
    private String[] nombresDePartidos;
    private String[] nombresDeDistritos;
    private int[] bancasPorDistritos;
    private int[] rangoMesas;
    private int[] votosPresidenciales;
    private int[][] votosDiputados;
    public int primero;
    public int segundo;
    private int total_votos_presidente;
    private int[] total_votos_diputados;
    private int[][] resultadosDiputados;
    private T[][] cocientesPorDistrito;
    private Boolean[] seRepartioBancas;

    public class VotosPartido{
        private int presidente;
        private int diputados;
        VotosPartido(int presidente, int diputados){this.presidente = presidente; this.diputados = diputados;}
        public int votosPresidente(){return presidente;}
        public int votosDiputados(){return diputados;}
    }
    // Iniciar el sistema (SistemaCNE()) es O(P*D) ya que la operacion mas costosa es crear la matriz (arreglo de arreglos) que tine P columnas y D filas.
    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        nombresDePartidos = nombresPartidos;
        nombresDeDistritos = nombresDistritos;
        bancasPorDistritos = diputadosPorDistrito;
        rangoMesas = ultimasMesasDistritos;
        votosPresidenciales = new int[nombresPartidos.length];
        votosDiputados = new int[nombresDistritos.length][nombresPartidos.length];
        primero = 0;
        segundo = 0;
        total_votos_presidente = 0;
        total_votos_diputados = new int[nombresDistritos.length];
        resultadosDiputados = new int[nombresDistritos.length][nombresPartidos.length];
        cocientesPorDistrito = new T[nombresDistritos.length][nombresPartidos.length]; 
        seRepartioBancas = new Boolean[nombresDistritos.length];
    }

    // nombrePartido es O(1) ya que accede a la posicion correspondiente del arreglo nombresDePartidos (y esto cuesta O(1)).
    public String nombrePartido(int idPartido) {
        return nombresDePartidos[idPartido];
    }
    
    // nombreDistrito es O(1) ya que accede a la posicion correspondiente del arreglo nombresDeDistritos (y esto cuesta O(1)).
    public String nombreDistrito(int idDistrito) {
        return nombresDeDistritos[idDistrito];
    }

    // diputadosEnDisputa es O(1) ya que accede a la posicion correspondiente del arreglo bancasPorDistritos (y esto cuesta O(1)).
    public int diputadosEnDisputa(int idDistrito) {
        return bancasPorDistritos[idDistrito];
    }

    // indiceDistritoDeMesa es O(log(D)) por que realiza busqueda binaria en un arreglo de longitud D (el arreglo rangoMesas)
    private int indiceDistritoDeMesa(int idMesa){
        int inicio = 0;
        int fin = rangoMesas.length;
        while (inicio < fin) {
            int medio = (inicio + fin) /2;
            if (rangoMesas[medio] <= idMesa){
                inicio = medio +1;
            } else {
                fin = medio;
            }
        }
        return inicio;
    }

    // distritoDeMesa es O(log(D)) ya que llama a la funcion indiceDistritoDeMesa (ya justificado que es O(log(D)) arriba) y luego busca en un arreglo el nombre correspondiente que cuesta O(1) (max = log(D))
    public String distritoDeMesa(int idMesa) {
        return nombresDeDistritos[indiceDistritoDeMesa(idMesa)];
    }

    private void encontrarPrimeroSegundo(int[ ] votos){
        primero = 0;
        segundo = 0;
        for (int i = 0; i < votos.length - 1; i++) {
            if (votos[i] >= primero) {
                segundo = primero;
                primero = votos[i];
            } else if (votos[i] > segundo) {
                segundo = votos[i];
            }
        }
    }

    // registrarMesa es O(P + log(D)) ya que  usamos un heap para cada distrito (crear el heap con el algoritmo de Floyd es O(P)) y para calcular a que distrito estamos cargando los votos de diputados usamos indiceDistritoDeMesa que cuesta O(log(D)
    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        //Registra votos a presidente
        for(int i = 0; i < nombresDePartidos.length; i++){
            total_votos_presidente = total_votos_presidente + actaMesa[i].presidente;
            votosPresidenciales[i] = votosPresidenciales[i] + actaMesa[i].presidente;
        }

        //Actualiza primero y segundo
        encontrarPrimeroSegundo(votosPresidenciales);
        
        //Registra votos a diputados del distrito correspondiente
        int distrito = indiceDistritoDeMesa(idMesa);
        for(int j = 0; j < nombresDePartidos.length; j++){
            votosDiputados[distrito][j] = votosDiputados[distrito][j] + actaMesa[j].diputados;
            total_votos_diputados[distrito] = total_votos_diputados[distrito] + votosDiputados[distrito][j];
            seRepartioBancas[distrito] = false;
            if(cocientesPorDistrito[distrito][j] == null){
                cocientesPorDistrito[distrito][j] = new T(j,0);
            }
        }

        //Actualiza cocientesPorDistrito
        for(int m = 0; m < votosDiputados[distrito].length - 1; m++){
            cocientesPorDistrito[distrito][m].id = m;
            if(votosDiputados[distrito][m] * 100 / total_votos_diputados[distrito] >= 3){
                cocientesPorDistrito[distrito][m].votos_totales = votosDiputados[distrito][m];
                cocientesPorDistrito[distrito][m].cociente = votosDiputados[distrito][m];
            }
        }
        //Ordena el arr para representarlo como heap
        T.toHeap(cocientesPorDistrito[distrito]);
    }

    // votosPresidenciales es O(1) ya que accede a la posicion correspondiente del arreglo votosPresidenciales (y esto cuesta O(1)).
    public int votosPresidenciales(int idPartido) {
        return votosPresidenciales[idPartido];
    }

    // votosDiputados es O(1) ya que accede a la posicion correspondiente del arreglo votosDiputados (y esto cuesta O(1)).
    public int votosDiputados(int idPartido, int idDistrito) {
        return votosDiputados[idDistrito][idPartido];
    }

    // resultadosDiputados es O(Dd ∗ log(P) ya que calcular una banca cuesta O(log(P)) (por que reOrdenar un heap cuesta eso) y esto hay que hacerlo para la cantidad de bancas en disputa en este distrito.
    public int[] resultadosDiputados(int idDistrito){
        if(!seRepartioBancas[idDistrito]){
            for(int i = 0; i < bancasPorDistritos[idDistrito]; i++){
            T max = cocientesPorDistrito[idDistrito][0];
            max.bancas_obtenidas++;
            resultadosDiputados[idDistrito][max.id] = max.bancas_obtenidas;
            max.cociente = max.votos_totales / (max.bancas_obtenidas + 1);
            T.reOrdenaHeap(cocientesPorDistrito[idDistrito], max, 0);
            seRepartioBancas[idDistrito] = true;
        }
        }
        return resultadosDiputados[idDistrito];
    }

    // hayBallotage es O(1) ya que guardamos como variables el primero y el segundo, luego calcular su porcentaje y decidir si hay balotage o no son todas operaciones de costo O(1)
    public boolean hayBallotage(){
        int porcentaje_primero = primero * 100 / total_votos_presidente;
        int porcentaje_segundo = segundo * 100 / total_votos_presidente;
        if(porcentaje_primero >= 45){
            return false;
        } else if(porcentaje_primero >= 40 &&  (porcentaje_primero - porcentaje_segundo) >= 10){
            return false;
        }
        return true;
    }
}


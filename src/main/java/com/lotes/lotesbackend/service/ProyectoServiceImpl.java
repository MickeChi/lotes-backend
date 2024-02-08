package com.lotes.lotesbackend.service;

import java.util.*;
import java.util.stream.Collectors;

import com.lotes.lotesbackend.constants.TipoFraccion;
import com.lotes.lotesbackend.dto.FraccionTextoDTO;
import com.lotes.lotesbackend.dto.ProyectoTextoDTO;
import com.lotes.lotesbackend.utils.FraccionMaps;
import com.lotes.lotesbackend.utils.GenericMapper;
import com.lotes.lotesbackend.utils.NumberUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotes.lotesbackend.dto.FraccionExternaDTO;
import com.lotes.lotesbackend.dto.ProyectoDTO;
import com.lotes.lotesbackend.entity.Cota;
import com.lotes.lotesbackend.entity.Fraccion;
import com.lotes.lotesbackend.entity.Proyecto;
import com.lotes.lotesbackend.repository.CotaRepository;
import com.lotes.lotesbackend.repository.FraccionRepository;
import com.lotes.lotesbackend.repository.ProyectoRepository;

@Service
public class ProyectoServiceImpl implements ProyectoService{

	@Autowired
	private ProyectoRepository proyectoRepository;

	@Autowired
	FraccionRepository fraccionRepository;

	@Autowired
	CotaRepository cotaRepository;

	private final ModelMapper modelMapper;

	public ProyectoServiceImpl() {
		this.modelMapper = GenericMapper.getMapper();
		this.modelMapper.addMappings(FraccionMaps.fraccionExternaDtoMap);

	}

	@Override
	public List<ProyectoDTO> findAll() {

		return this.proyectoRepository.findAll()
				.stream().map( p-> this.modelMapper.map(p, ProyectoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProyectoDTO> findById(Long id) {
		Optional<ProyectoDTO> proyectoDtoOp = Optional.empty();
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(id);
		if(proyOp.isPresent()) {
			List<FraccionExternaDTO> listFraccionesExt = this.fraccionRepository.findByProyectoIdAndColindanciaProyecto(proyOp.get().getId(), true)
					.stream().map(p-> this.modelMapper.map(p, FraccionExternaDTO.class)).collect(Collectors.toList());
			ProyectoDTO proyDto = this.modelMapper.map(proyOp.get(), ProyectoDTO.class);
			proyDto.setFraccionesExternas(listFraccionesExt);
			proyectoDtoOp = Optional.of(proyDto);
		}
		return proyectoDtoOp;
	}

	@Override
	public ProyectoDTO save(ProyectoDTO proyectoDto) {

		Proyecto proy = this.proyectoRepository.save(this.modelMapper.map(proyectoDto, Proyecto.class));

		List<FraccionExternaDTO> fraccionExternasDto =  proyectoDto.getFraccionesExternas().stream().map(cp -> {
			Fraccion frac = new Fraccion();
			frac.setDescripcion(cp.getDescripcion());
			frac.setProyecto(proy);
			frac.setColindanciaProyecto(cp.isColindanciaProyecto());


			frac = this.fraccionRepository.save(frac);

			Cota cotap = new Cota();
			cotap.setColindancias(new ArrayList<>());
			cotap.setFraccion(frac);
			cotap.setMedida(cp.getMedida());
			cotap.setOrden(cp.getOrden());
			cotap.setOrientacion(cp.getOrientacion());
			cotap.setTipoLinea(cp.getTipoLinea());
			cotap = this.cotaRepository.save(cotap);

			cp.setProyectoId(frac.getProyecto().getId());
			cp.setFraccionId(frac.getId());
			cp.setCotaId(cotap.getId());

			return cp;
		}).collect(Collectors.toList());

		proyectoDto = this.modelMapper.map(proy, ProyectoDTO.class);
		proyectoDto.setFraccionesExternas(fraccionExternasDto);

		return proyectoDto;
	}

	@Override
	public ProyectoDTO update(ProyectoDTO proyectoDto) {

		Optional<Proyecto> proyOp = this.proyectoRepository.findById(proyectoDto.getId());
		if(proyOp.isPresent()) {
			Proyecto proy = this.proyectoRepository.save(this.modelMapper.map(proyectoDto, Proyecto.class));

			List<FraccionExternaDTO> fraccionExternasDto = proyectoDto.getFraccionesExternas().stream().map(cp -> {
				Fraccion frac = new Fraccion();
				if(cp.getFraccionId() != null){
					Optional<Fraccion> fracOp = this.fraccionRepository.findById(cp.getFraccionId());
					if (fracOp.isPresent()){
						frac = fracOp.get();
					}
				}
				frac.setDescripcion(cp.getDescripcion());
				frac.setProyecto(proy);
				frac.setColindanciaProyecto(cp.isColindanciaProyecto());

				frac = this.fraccionRepository.save(frac);

				Cota cotap = new Cota();
				if(cp.getCotaId() != null){
					Optional<Cota> cotaOp = this.cotaRepository.findById(cp.getCotaId());
					if (cotaOp.isPresent()){
						cotap = cotaOp.get();
					}
				}

				cotap.setColindancias(new ArrayList<>());
				cotap.setFraccion(frac);
				cotap.setMedida(cp.getMedida());
				cotap.setOrden(cp.getOrden());
				cotap.setOrientacion(cp.getOrientacion());
				cotap.setTipoLinea(cp.getTipoLinea());
				cotap = this.cotaRepository.save(cotap);

				cp.setProyectoId(frac.getProyecto().getId());
				cp.setFraccionId(frac.getId());
				cp.setCotaId(cotap.getId());

				return cp;
			}).collect(Collectors.toList());

			proyectoDto = this.modelMapper.map(proy, ProyectoDTO.class);
			proyectoDto.setFraccionesExternas(fraccionExternasDto);

		}

		return proyectoDto;

	}

	@Override
	public FraccionExternaDTO saveFraccionExterna(FraccionExternaDTO fraccionExternaDto) {
		Optional<Proyecto> proyOp = this.proyectoRepository.findById(fraccionExternaDto.getProyectoId());
		if(proyOp.isPresent()) {
			Fraccion frac = new Fraccion();
			frac.setDescripcion(fraccionExternaDto.getDescripcion());
			frac.setProyecto(proyOp.get());

			frac = this.fraccionRepository.save(frac);

			Cota cotap = new Cota();
			cotap.setColindancias(new ArrayList<>());
			cotap.setFraccion(frac);
			cotap.setMedida(fraccionExternaDto.getMedida());
			cotap.setOrden(fraccionExternaDto.getOrden());
			cotap.setOrientacion(fraccionExternaDto.getOrientacion());
			cotap.setTipoLinea(fraccionExternaDto.getTipoLinea());
			cotap = this.cotaRepository.save(cotap);

			fraccionExternaDto.setProyectoId(frac.getProyecto().getId());
			fraccionExternaDto.setFraccionId(frac.getId());
			fraccionExternaDto.setCotaId(cotap.getId());

			return fraccionExternaDto;
		}


		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProyectoTextoDTO generateFraccionesText(Long proyectoId) {

		String descripcionTemplate = "V_USO V_CLASE ubicado en la localidad V_LOCALIDADy municipio de V_MUNICIPIO V_ESTADO, marcado como " +
				"tablaje catastral V_TXT_TABLAJE_CATASTRAL, cuya superficie de terreno " +
				"es de V_SUPERFICIE_TERRENOm2 (V_TXT_SUPERFICIE_TERRENO metros cuadrados). " +
				"Polígono de figura irregular que se describe a continuación: partiendo del vértice V_PUNTO_PARTIDA ";

		String colindanciaTemplate = "con rumbo al V_ORIENTACION en línea V_TIPO_LINEA colindando con V_LOTE_DESCRIPCION, " +
				"mide V_MEDIDAm (V_TXT_MEDIDA metros)";

		Optional<Proyecto> proOp = this.proyectoRepository.findById(proyectoId);


		ProyectoTextoDTO proyectoTexto = new ProyectoTextoDTO();
		List<FraccionTextoDTO> fraccionesTxt = new ArrayList<>();
		proyectoTexto.setFraccionesTxt(fraccionesTxt);
		if(proOp.isPresent()){
			Proyecto py = proOp.get();
			proyectoTexto.setProyectoId(py.getId());
			List<Fraccion> fracciones = fraccionRepository.findByProyectoIdAndColindanciaProyecto(proyectoId, false);
			int contFracciones = 1;
			for(Fraccion f: fracciones ){

				List<Cota> cotas = cotaRepository.getCotasByFraccionId(f.getId());
				if(cotas.isEmpty()){
					continue;
				}
				FraccionTextoDTO fracTexto = new FraccionTextoDTO();
				fracTexto.setFraccionId(f.getId());

				StringBuilder sb = new StringBuilder();
				String tablajeCatastralTxt = NumberUtils.numeroATexto(f.getTablaje().toString());
				String superficieTerrenoTxt = NumberUtils.numeroATexto(f.getSuperficieTerreno().toString());
				String numeroRomanoFraccion = NumberUtils.numeroARomanos(contFracciones) + ".- ";
				String localidad = (py.getLocalidad() != null) ? "de " + py.getLocalidad() + " " : "";

				sb.append(numeroRomanoFraccion);
				sb.append(
						descripcionTemplate
								//.replaceAll("V_USO", f.getUso().getTipo())
								//.replaceAll("V_CLASE", f.getClase())
								.replaceAll("V_USO", "Solar")
								.replaceAll("V_CLASE", f.getUso().getTipo())
								.replaceAll("V_LOCALIDAD", localidad)
								.replaceAll("V_MUNICIPIO", py.getMunicipio())
								.replaceAll("V_ESTADO", py.getEstado())
								.replaceAll("V_TXT_TABLAJE_CATASTRAL", tablajeCatastralTxt)
								.replaceAll("V_SUPERFICIE_TERRENO", f.getSuperficieTerreno().toString())
								.replaceAll("V_TXT_SUPERFICIE_TERRENO", superficieTerrenoTxt)
								.replaceAll("V_PUNTO_PARTIDA", py.getPuntoPartida().getNombre().toLowerCase())
				);



				int contCota = 1;
				int cotasSize = cotas.size();
				for (Cota c : cotas){
					String textoExtra1 = "";
					if(contCota > 1 && contCota < cotasSize){
						textoExtra1 =  ", de este punto ";
					}else if(contCota == cotasSize){
						textoExtra1 =  ", y para cerrar el polígono que se describe ";
					}

					List<Fraccion> colindanciasList = c.getColindancias();
					Fraccion colindancia = colindanciasList.get(0);
					String loteDescripcion = null;
					if(colindancia.isColindanciaProyecto()){
						loteDescripcion = NumberUtils.allNumbersToText(colindancia.getDescripcion());
					}else{
						loteDescripcion = "tablaje catastral " + NumberUtils.numeroATexto(colindancia.getTablaje().toString()) + " ";
						if(colindancia.getTipoFraccion() != null && colindancia.getTipoFraccion().equals(TipoFraccion.VIALIDAD)){
							loteDescripcion += "(" + colindancia.getTipoFraccion().getTipo() + ") ";
						}
						loteDescripcion += "resultante de la presente división";
					}

					String medidaTxt = NumberUtils.numeroATexto(c.getMedida().toString());

					sb.append(textoExtra1);
					sb.append(
							colindanciaTemplate
									.replaceAll("V_ORIENTACION", c.getOrientacion().getNombre().toLowerCase())
									.replaceAll("V_TIPO_LINEA", c.getTipoLinea().getNombre().toLowerCase())
									.replaceAll("V_LOTE_DESCRIPCION", loteDescripcion)
									.replaceAll("V_MEDIDA", c.getMedida().toString())
									.replaceAll("V_MEDIDA", c.getMedida().toString())
									.replaceAll("V_TXT_MEDIDA", medidaTxt)
					);

					contCota++;
				}

				sb.append(". ");
				fracTexto.setFraccionTexto(sb.toString());
				fraccionesTxt.add(fracTexto);

				contFracciones++;
			};
		}

		return proyectoTexto;
	}



}

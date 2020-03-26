	package com.workshop.main.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.workshop.main.model.TsscGame;
import com.workshop.main.model.TsscTopic;
import com.workshop.main.repositories.TsscGameRepository;


@Service
public class TsscGameServiceImp implements TsscGameService {
	
	@Autowired
	private TsscGameRepository game;
	@Autowired
	private TsscTopicServiceImp repo;

	@Autowired
	public TsscGameServiceImp(TsscGameRepository game, TsscTopicServiceImp repo) {
		super();
		this.game = game;
		this.repo = repo;
	}

	@Override
	public TsscGame addGameT(TsscGame g, long ids) {
		if (g.getNGroups() > 0 && g.getNSprints() > 0) {
			TsscTopic find = repo.findTopic(ids);
			if (find != null) {

				g.setTsscTopic(find);
				game.save(g);
				return g;
			}

			else {

				game.save(g);
				return g;
			}

		}

		return null;
	}

	
	
	
	@Override
	public TsscGame addGame(TsscGame g) {
		if (g.getNGroups() > 0 && g.getNSprints() > 0) {

			game.save(g);
			return g;
		}

		return null;

	}

	@Override
	public TsscGame setGame(TsscGame g, int groups, String name) {

		if (g != null && groups > 0 && name != null&&!name.equals("")) {
			g.setName(name);
			g.setNGroups(groups);
			game.save(g);
			return g;
		}

		return null;
	}

	@Override
	public TsscGame addGame2(TsscGame g, long id) {

		if (g.getNGroups() > 0 && g.getNSprints() > 0) {
			TsscTopic find = repo.findTopic(id);
		
			if (find != null) {

				g.setTsscStories(find.getTsscStories());
				g.setTsscTimecontrol(find.getTsscCronograma());
				g.setTsscTopic(find);
				game.save(g);
				return g;

			}

		}
		return null;
	}

	@Override
	public TsscGame findGame(Long id) {
		if (game.findById(id).get() != null){
			 return game.findById(id).get();
		}
		return null;
	}
}